package org.techtown.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.databinding.FragmentUserBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepositoryImpl
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.retorfit.RetrofitBuilder


class UserFragment : Fragment(),
    UserListAdapter.onUserClickListener,
    UserListAdapter.onFavClickListener {

    //onDestroy때 완전 제거를 위해 null허용.
    private var _binding: FragmentUserBinding? = null

    //매번 null체크 번거러움 제거.
    private val binding get() = _binding!!

    //유저 리스트 어댑터.
    private lateinit var userListAdapter: UserListAdapter

    //받아온 유저 리스트.
    private lateinit var userList: ArrayList<UserModel>

    //현재 검색 쿼리.
    private var currentQuery = ""

    //초기 페이지.
    private var currentPage = 1

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var compositeDisposable = CompositeDisposable()

    //repository setting
    private val userRepository: UserRepository by lazy {
        //remote 데이터 세팅.
        val remoteDataSource = RemoteDataSourceImpl(api = RetrofitBuilder.api)
        val localDataSource = LocalDataSourceImpl(userDatabase = UserDatabase.getInstance(context))
        UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //초기유저정보를 세팅해줌.
        setUserInfo()
        setClickLisener()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun setClickLisener() {
        //리사이클러뷰 스크롤 리스너등록.
        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (binding.rvUser.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = (binding.rvUser.adapter?.itemCount ?: 1) - 1
                if (lastVisibleItemPosition == itemTotalCount) {
                    currentPage += 1
                    getUserInfo(currentQuery, false)
                }
            }
        })
    }

    private fun setUserInfo() {

        userList = arguments?.getParcelableArrayList<UserModel>("user_list") as ArrayList<UserModel>
        currentQuery = arguments?.getString("first_query") as String

        //어댑터 연결부분.
        userListAdapter =
            UserListAdapter(
                requireActivity(),
                { userModel: UserModel, view: View, i: Int ->
                    onUserClick(userModel, view, i)
                }) { userModel: UserModel, view: View, i: Int ->
                onFavClick(userModel, view, i)
            }
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }

        //ListAdapter는 notify필요없이 submitList로 item비교 전달 및 MainThread ui업데이트해줌 (observer는 lifecycler영향을 받으므로 화면 onstart될때밑에 로직을 타게되있음).
        //검색화면 목록 즐겨찾기여부 확인하는데 사용.
        userRepository.getFavUserInfo(true)?.observe(viewLifecycleOwner,
            { item ->
                Log.d("Database", "유저화면 즐겨찾기 목록 업데이트 완료.")

                for (i in 0 until userList.size) {
                    for (element in item) {
                        if (userList[i].id == element.id) {
                            userList[i].is_favorite = true
                            break
                        } else {
                            userList[i].is_favorite = false
                        }
                    }
                }
                userListAdapter.submitList(userList)
            })
    }

    //검색을 통한 유저정보 가져와줌.
    private fun getUserInfo(query: String?, isSearch: Boolean) {
        Util.showProgress(requireActivity())
        if (isSearch) { //검색일땐 첫페이지부터 보여줘야되므로 1로 넣어줌.
            currentPage = 1
        }

        userRepository.getUserInfo(query, currentPage, Const.PER_PAGE_LIST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Util.closeProgress()
                if (isSearch) {//검색일떄.
                    userList.clear()
                    userList.addAll(it.items)
                    userListAdapter.submitList(userList.distinct().toList())
                } else { //페이징일떄.
                    userList.addAll(it.items)
                    userListAdapter.submitList(userList.distinct().toList())
                }
            }, {
                Util.closeProgress()
                Toast.makeText(activity, "데이터 불러오기 실패", Toast.LENGTH_LONG).show()
            }).addTo(compositeDisposable)
    }

    //검색창 세팅. 
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)

        val item = menu.findItem(R.id.search)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)

        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                currentQuery = query.toString()
                getUserInfo(currentQuery, true)
                //검색후 포커스가 한번더 searchView에잡히기때문에 두번 검색이 되므로 클리어시켜줌.
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchView.setOnClickListener { view -> }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onUserClick(model: UserModel, v: View, position: Int) {
        val intent = Intent(activity, UserDetailActivity::class.java)
        intent.putExtra("user_model", model)
        startActivity(intent)
    }

    override fun onFavClick(model: UserModel, v: View, position: Int) {
        model.is_favorite = true
        //즐겨찾기 추가.
        userRepository.setFavUserInfo(model) {
            Log.d("Database", "제대로 저장 완료 ${it} 즐겨찾기 완료.")
        }
    }
}