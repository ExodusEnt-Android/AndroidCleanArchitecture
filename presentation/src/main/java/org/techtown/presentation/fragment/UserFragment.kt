package org.techtown.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.presentation.util.Const
import org.techtown.presentation.R
import org.techtown.presentation.util.Util
import org.techtown.presentation.activity.UserDetailActivity
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.databinding.FragmentUserBinding
import org.techtown.presentation.model.PresentationUserModel
import org.techtown.presentation.viewmodel.UserViewModel


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
    private lateinit var userList: ArrayList<PresentationUserModel>

    //현재 검색 쿼리.
    private var currentQuery = ""

    //초기 페이지.
    private var currentPage = 1

    private val userViewModel: UserViewModel by activityViewModels()

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
        initSet()
        getDataFromViewModel()
        setClickLisener()
    }

    override fun onDestroy() {
        super.onDestroy()
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

    private fun initSet() {
        currentQuery = arguments?.getString("first_query") as String

        //어댑터 연결부분.
        userListAdapter =
            UserListAdapter(
                { userModel: PresentationUserModel, view: View, i: Int ->
                    onUserClick(userModel, view, i)
                }) { userModel: PresentationUserModel, view: View, i: Int ->
                onFavClick(userModel, view, i)
            }
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }

        //스플래시에서 넘어온데이터 세팅.
        splashDataSetting()
    }

    private fun splashDataSetting() {
        userList = arguments?.getParcelableArrayList<PresentationUserModel>("user_list") as ArrayList<PresentationUserModel>

        if (!userList.isNullOrEmpty()) {
            userViewModel.setSearchUserList(userList.map { it.copy() })
            userViewModel.initialListSetting()
        }
    }

    private fun getDataFromViewModel() {
        userViewModel.userFragmentUpdateUserList.subscribe({
            Util.closeProgress()

            userListAdapter.submitList(it.toMutableList())
        }, {
            Toast.makeText(activity, it.message.toString(), Toast.LENGTH_SHORT)
                .show()
        })
    }

    //검색을 통한 유저정보 가져와줌.
    private fun getUserInfo(query: String?, isSearch: Boolean) {
        Util.showProgress(requireActivity())
        if (isSearch) { //검색일땐 첫페이지부터 보여줘야되므로 1로 넣어줌.
            currentPage = 1
        }

        userViewModel.searchUser(query, currentPage, Const.PER_PAGE_LIST, isSearch)
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

    override fun onUserClick(model: PresentationUserModel, v: View, position: Int) {
        val intent = Intent(activity, UserDetailActivity::class.java)
        intent.putExtra("user_model", model)
        startActivity(intent)
    }

    override fun onFavClick(model: PresentationUserModel, v: View, position: Int) {
        if (!model.is_favorite) {
            (v as AppCompatImageView).setBackgroundResource(R.drawable.star_selected_36)
            model.is_favorite = true
            userViewModel.setFavUser(model)
        } else {
            model.is_favorite = false
            (v as AppCompatImageView).setBackgroundResource(R.drawable.star_unselected_36)
            userViewModel.deleteFavUser(model)
        }
    }
}