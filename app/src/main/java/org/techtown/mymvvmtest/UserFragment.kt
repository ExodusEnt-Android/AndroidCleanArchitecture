package org.techtown.mymvvmtest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.mymvvmtest.adapter.UserListAdapter
import org.techtown.mymvvmtest.databinding.FragmentUserBinding
import org.techtown.mymvvmtest.model.UserModel
import org.techtown.mymvvmtest.model.UserRootModel
import org.techtown.mymvvmtest.retorfit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment : Fragment() {

    //onDestroy때 완전 제거를 위해 null허용.
    private var _binding: FragmentUserBinding? = null
    //매번 null체크 번거러움 제거.
    private val binding get() = _binding!!

    //유저 리스트 어댑터.
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //초기유저정보를 가져옵니다.
        getUserInfo()
    }

    private fun getUserInfo() {
        RetrofitBuilder.api.getUserInfo("hello").enqueue(object : Callback<UserRootModel>{
            override fun onResponse(call: Call<UserRootModel>, response: Response<UserRootModel>) {
                if(response.isSuccessful){
                    if(response.code() == 200){
                        val userList = response.body()!!.items

                        //어댑터 연결부분.
                        userListAdapter = UserListAdapter()
                        binding.rvUser.apply {
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                            adapter = userListAdapter
                        }
                        //ListAdapter는 notify필요없이 submitList로 item비교 전달 및 MainThread ui업데이트해줌.
                        userListAdapter.submitList(userList)

                    }
                }
            }

            override fun onFailure(call: Call<UserRootModel>, t: Throwable) {
                Toast.makeText(activity, "데이터 불러오기 실패", Toast.LENGTH_LONG).show()
            }
        })
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
}