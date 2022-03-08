package org.techtown.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import org.techtown.presentation.databinding.ActivityMainBinding
import org.techtown.presentation.model.UserModel

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

    override fun onResume() {
        super.onResume()
        lastTime = 0L
        firstTime = 0L
        isBackPressed = false
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
        supportFragmentManager.beginTransaction().replace(binding.liContainer.id, userFragment).commit()
    }

    override fun onBackPressed() {
        lastTime = SystemClock.elapsedRealtime()
        //만약 두번째 빠르게 눌렀을경우엔 firstTime이 초기화 되지 않기때문에 맨처음 초기화된 시간이 들어가서 lastTime이랑비교해줌.
        if(isBackPressed && (lastTime - firstTime < 2000L)){
            super.onBackPressed()
        } else { 
            firstTime = SystemClock.elapsedRealtime()
            isBackPressed = true
            Toast.makeText(this, "한번더 눌러주세요.", Toast.LENGTH_SHORT).show()
        }

    }
}