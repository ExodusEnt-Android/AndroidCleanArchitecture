package org.techtown.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.techtown.presentation.fragment.MyFavoritesFragment
import org.techtown.presentation.fragment.UserFragment
import org.techtown.presentation.databinding.ActivityMainBinding
import org.techtown.presentation.model.UserModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    //유저화면, 즐겨찾기 화면.
    private lateinit var userFragment: UserFragment
    private lateinit var myFavoritesFragment: MyFavoritesFragment

    //뷰바인딩 추가
    private lateinit var binding: ActivityMainBinding

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var compositeDisposable = CompositeDisposable()

    private val backButtonSubject: Subject<Long> = BehaviorSubject.createDefault(0L).toSerialized()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSet()
        initClick()
    }

    //클릭 리스너 등록.
    private fun initClick() {
        binding.btnUser.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.liContainer.id, userFragment)
                .commit()
        }

        binding.btnFavorites.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.liContainer.id, myFavoritesFragment).commit()
        }
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

        //처음엔 유저 화면 보여줌.
        supportFragmentManager.beginTransaction().replace(binding.liContainer.id, userFragment)
            .commit()

        backButtonSubject.toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(2, 1)
            .map { Pair(it[0], it[1]) }
            .map { it.second - it.first < TimeUnit.SECONDS.toMillis(2) }
            .subscribe { isFinish ->
                if (isFinish) {
                    finish()
                } else {
                    Toast.makeText(this, "한번더 눌러주세요.", Toast.LENGTH_SHORT).show()
                }
            }.addTo(compositeDisposable)
    }

    override fun onBackPressed() {
        backButtonSubject.onNext(System.currentTimeMillis())
    }
}