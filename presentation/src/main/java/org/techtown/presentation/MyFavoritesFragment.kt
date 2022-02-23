package org.techtown.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.presentation.adapter.UserListAdapter
import org.techtown.presentation.databinding.FragmentMyFavoritesBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.repository.UserRepositoryImpl
import org.techtown.presentation.retorfit.RetrofitBuilder


class MyFavoritesFragment : Fragment(),
    UserListAdapter.onFavClickListener{

    private var _binding: FragmentMyFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter

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
    ): View {
        _binding = FragmentMyFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSet()
    }

    private fun initSet() {

        userListAdapter = UserListAdapter(
            requireActivity(),
            null
        ) { userModel: UserModel, view: View, i: Int ->
            onFavClick(userModel, view, i)
        }

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }

        userRepository.getFavUserInfo(true)?.observe(viewLifecycleOwner,
            {
                Log.d("Database", "즐겨찾기 화면 목록 업데이트 완료.")
                userListAdapter.submitList(it)
            })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFavoritesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onFavClick(model: UserModel, v: View, position: Int) {
        Log.d("Database", "즐겨찾기 해제 완료.")
        model.is_favorite = false
        //즐겨찾기 해제
        userRepository.deleteFavUserInfo(model.id) {
            Log.d("Database", "제대로 삭제 완료 ${it}")
        }
    }
}