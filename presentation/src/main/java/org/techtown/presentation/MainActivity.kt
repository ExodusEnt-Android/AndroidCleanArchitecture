package org.techtown.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //유저화면, 즐겨찾기 화면.
    private lateinit var userFragment: UserFragment
    private lateinit var myFavoritesFragment: MyFavoritesFragment

    //뷰바인딩 추가
    private lateinit var binding: ActivityMainBinding

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
        userFragment = UserFragment()
        myFavoritesFragment = MyFavoritesFragment()

        //처음엔 유저 화면 보여줌.
        supportFragmentManager.beginTransaction().replace(binding.liContainer.id, userFragment).commit()
    }
}