package org.techtown.presentation

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.adapter.UserViewHolder
import org.techtown.presentation.databinding.FragmentUserBinding
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.model.UserRootModel
import org.techtown.presentation.retorfit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment : Fragment(),
    UserViewHolder.onUserClickListener {

    //onDestroy때 완전 제거를 위해 null허용.
    private var _binding: FragmentUserBinding? = null
    //매번 null체크 번거러움 제거.
    private val binding get() = _binding!!

    //유저 리스트 어댑터.
    private lateinit var userListAdapter: UserListAdapter

    //받아온 유저 리스트.
    private lateinit var userList: ArrayList<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //초기유저정보를 세팅해줌.
        setUserInfo()
    }

    private fun setUserInfo() {

        userList = arguments?.getParcelableArrayList<UserModel>("user_list") as ArrayList<UserModel>

        //어댑터 연결부분.
        userListAdapter = UserListAdapter(requireActivity()){ userModel: UserModel, view: View, i: Int ->
            onUserClick(userModel, view, i)
        }
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }
        //ListAdapter는 notify필요없이 submitList로 item비교 전달 및 MainThread ui업데이트해줌.
        userListAdapter.submitList(userList)
    }

    //검색을 통한 유저정보 가져와줌.
    private fun getUserInfo(query: String?){
        Util.showProgress(requireActivity())
        RetrofitBuilder.api.getUserInfo(query, Const.START_PAGE, Const.PER_PAGE_LIST).enqueue(object : Callback<UserRootModel> {
            override fun onResponse(call: Call<UserRootModel>, response: Response<UserRootModel>) {
                Util.closeProgress()
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val userList = response.body()!!.items
                        userListAdapter.submitList(userList)
                    }
                }
            }

            override fun onFailure(call: Call<UserRootModel>, t: Throwable) {
                Util.closeProgress()
                Toast.makeText(activity, "데이터 불러오기 실패", Toast.LENGTH_LONG).show()
            }
        })
    }

    //검색창 세팅. 
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)

        val item = menu.findItem(R.id.search)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)

        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getUserInfo(query)
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
        intent.putExtra("user_model",model)
        startActivity(intent)
    }
}