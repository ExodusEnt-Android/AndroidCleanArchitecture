package org.techtown.mymvvmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.mymvvmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //유저화면, 즐겨찾기 화면.
    private lateinit var userFragment: UserFragment
    private lateinit var myFavoritesFragment: MyFavoritesFragment

    //뷰바인딩 추가
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initSet()
        initClick()
    }
    }

    //초기 설정.
    private fun initSet(){
        userFragment = UserFragment()
        myFavoritesFragment = MyFavoritesFragment()

        //처음엔 유저 화면 보여줌.
        supportFragmentManager.beginTransaction().replace(R.id.cl_container, userFragment).commit()
    }
}