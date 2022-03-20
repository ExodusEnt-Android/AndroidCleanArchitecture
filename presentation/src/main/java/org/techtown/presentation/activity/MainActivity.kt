package org.techtown.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.techtown.presentation.R
import org.techtown.presentation.fragment.MyFavoritesFragment
import org.techtown.presentation.fragment.UserFragment
import org.techtown.presentation.databinding.ActivityMainBinding
import org.techtown.presentation.datasource.local.LocalDataSourceImpl
import org.techtown.presentation.datasource.remote.RemoteDataSourceImpl
import org.techtown.presentation.db.UserDatabase
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.repository.UserRepositoryImpl
import org.techtown.presentation.retorfit.RetrofitBuilder
import org.techtown.presentation.viewmodel.MainViewModelFactory
import org.techtown.presentation.viewmodel.UserViewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    //유저화면, 즐겨찾기 화면.
    private lateinit var userFragment: UserFragment
    private lateinit var myFavoritesFragment: MyFavoritesFragment

    //뷰바인딩 추가
    private lateinit var binding: ActivityMainBinding

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var compositeDisposable = CompositeDisposable()

    //repository setting
    private val userRepository: UserRepository by lazy {
        //remote 데이터 세팅.
        val remoteDataSource = RemoteDataSourceImpl(api = RetrofitBuilder.api)
        val localDataSource = LocalDataSourceImpl(userDatabase = UserDatabase.getInstance(this))
        UserRepositoryImpl(remoteDataSource, localDataSource)
    }

    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(userRepository)).get(UserViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSet()
        dataFromViewModel()
        userViewModel.clickBackButton()
    }

    //초기 설정.
    private fun initSet() {

        //스플래시화면에서 받아온 초기 유저리스트 받아와주기.
        val userList =
            intent.getParcelableArrayListExtra<UserModel>("user_list") as ArrayList<UserModel>
        val firstQuery = intent.getStringExtra("first_query")

        //프래그먼트 데이터전송을위한 번들생성.
        val userBundle = Bundle()
        userBundle.putParcelableArrayList("user_list", userList)
        userBundle.putString("first_query", firstQuery)

        userFragment = UserFragment()
        userFragment.arguments = userBundle

        myFavoritesFragment = MyFavoritesFragment()

        binding.vpMain.adapter = MainViewpagerAdapter(this)

        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.btnMain.menu.getItem(position).isChecked = true
            }
        })

        binding.btnMain.setOnNavigationItemSelectedListener(this)
    }

    private fun dataFromViewModel() {
        userViewModel.mainBackPressPublishSubject.subscribe({ isBackPressPossible ->
            if (isBackPressPossible) {
                super.onBackPressed()
            } else {
                Toast.makeText(this, "한번더 눌러주세요.", Toast.LENGTH_SHORT).show()
            }

        }, {
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
        }).addTo(compositeDisposable)
    }

    override fun onBackPressed() {
        userViewModel.behaviorSubject.onNext(System.currentTimeMillis())
    }


    inner class MainViewpagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    userFragment
                }
                else -> {
                    myFavoritesFragment
                }
            }

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_user_frag -> {
                binding.vpMain.currentItem = 0
                true
            }
            R.id.item_fav_frag -> {
                binding.vpMain.currentItem = 1
                true
            }
            else -> {
                false
            }
        }
    }
}