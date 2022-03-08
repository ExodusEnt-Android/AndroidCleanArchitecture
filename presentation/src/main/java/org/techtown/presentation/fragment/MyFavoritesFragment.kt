package org.techtown.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
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

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var _compositeDisposable: CompositeDisposable? = null

    //여기서도 null체크 번거로움 제거.
    private val compositeDisposable get() = _compositeDisposable!!

    //즐찾화면에서 바로 제거하기위한 즐찾리스트 변수.
    private lateinit var favUserList: ArrayList<UserModel>

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

        _compositeDisposable = CompositeDisposable()

        userListAdapter = UserListAdapter(
            null
        ) { userModel: UserModel, view: View, i: Int ->
            onFavClick(userModel, view, i)
        }

        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }

        userRepository.getFavUserInfo(true)!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                userListAdapter.submitList(item)
                favUserList = item as ArrayList<UserModel>
            }, {
                Toast.makeText(activity, "즐찾화면에서 즐겨찾기 목록을 가져오는데 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }).addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        _binding = null
        compositeDisposable.dispose()
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
        userRepository.deleteFavUserInfo(model.id)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                Timber.d("Database::제대로 유저화면에서 삭제 ${model.login} 완료.")
                favUserList.remove(model)
                userListAdapter.submitList(favUserList)
            }, {
                Timber.d("Database::제대로 유저화면에서 삭제 실패 ${model.login}")
            })?.addTo(compositeDisposable)
    }
}