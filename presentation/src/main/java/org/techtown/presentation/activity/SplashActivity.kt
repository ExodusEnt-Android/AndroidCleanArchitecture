package org.techtown.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.*
import org.techtown.presentation.util.Util
import org.techtown.presentation.databinding.ActivitySplashBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.repository.UserRepositoryImpl
import org.techtown.presentation.retorfit.RetrofitBuilder
import org.techtown.presentation.viewmodel.SplashViewModel
import org.techtown.presentation.viewmodel.MainViewModelFactory
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    //처음 들어갈떄 임의용으로 정한 쿼리.
    private var firstQuery = "hello"

    //repository setting
    private val userRepository: UserRepository by lazy {
        //remote 데이터 세팅.
        val remoteDataSource = RemoteDataSourceImpl(api = RetrofitBuilder.api)
        val localDataSource = LocalDataSourceImpl(userDatabase = UserDatabase.getInstance(this))
        UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(userRepository)
        ).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이터 Subscribe.
        getDataFromViewModel()
        splashViewModel.searchUser()
    }

    private fun getDataFromViewModel() {
        splashViewModel.searchedUserPublishSubject.subscribe({
            goMainActivity(it as ArrayList<UserModel>?)
        }, {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT)
                .show()
            finish()
        })
    }

    private fun goMainActivity(searchUsers: ArrayList<UserModel>?){
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putParcelableArrayListExtra(PARAM_USER_LIST, searchUsers)
        intent.putExtra(PARAM_FIRST_QUERY, firstQuery)
        startActivity(intent)
        finish()
    }

    companion object {
        const val PARAM_USER_LIST = "user_list"
        const val PARAM_FIRST_QUERY = "first_query"
    }
}