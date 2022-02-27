package org.techtown.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import org.techtown.presentation.databinding.ActivityMainBinding
import org.techtown.presentation.model.UserModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    //유저화면, 즐겨찾기 화면.
    private lateinit var userFragment: UserFragment
    private lateinit var myFavoritesFragment: MyFavoritesFragment

    //뷰바인딩 추가
    private lateinit var binding: ActivityMainBinding

    //Back key눌림여부 확인.
    private var isBackPressed = false

    //Back key 시간초 재는거.
    private var lastTime = 0L
    private var firstTime = 0L

    //os gc가발동할떄 프로세스가 죽어버리니까 single객체 가로채야됨.
    private var compositeDisposable: CompositeDisposable? = null

    private val backButtonSubject: Subject<Long> = BehaviorSubject.createDefault(0L).toSerialized()

    override fun onResume() {
        super.onResume()
        lastTime = 0L
        firstTime = 0L
        isBackPressed = false
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable!!.dispose()
        compositeDisposable = null
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
            supportFragmentManager.beginTransaction().replace(binding.liContainer.id, userFragment).commit()
        }

        binding.btnFavorites.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(binding.liContainer.id, myFavoritesFragment).commit()
        }
    }

    //초기 설정.
    private fun initSet(){
    private fun initSet() {

        //항상 initSet할때마다 체크(Fragment tranction할때마다 onViewCreated가 불리므로 항상 이로직을 타게되어있음).
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }

        //스플래시화면에서 받아온 초기 유저리스트 받아와주기.
        val userList = intent.getParcelableArrayListExtra<UserModel>("user_list") as ArrayList<UserModel>
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
                if (isFinish && isBackPressed) {
                    finish()
                } else {
                    isBackPressed = true
                    Toast.makeText(this, "한번더 눌러주세요.", Toast.LENGTH_SHORT).show()
                }
            }.addTo(compositeDisposable!!)
    }

    override fun onBackPressed() {
        backButtonSubject.onNext(System.currentTimeMillis())
    }
}