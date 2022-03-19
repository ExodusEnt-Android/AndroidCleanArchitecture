package com.example.presentation.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.presentation.Fragment.FavoriteFragment
import com.example.presentation.R
import com.example.presentation.Fragment.UserSearchFragment
import com.example.presentation.Model.GitUserModel
import com.example.presentation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private var backClickTime : Long = 0    //뒤로가기 시간 재기 위함.

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gitUserModel : GitUserModel = intent.extras?.getParcelable<GitUserModel>(PARAM_USER) as GitUserModel

        val bundle = Bundle()
        bundle.putParcelable(PARAM_USER, gitUserModel)

        val fragment = UserSearchFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.ll_fragment, fragment).commit()

        //유저 검색하는 Fragment
        binding.btnUser.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.ll_fragment, UserSearchFragment()).commit()
        }

        //유저 즐겨찾기 보여주는 Fragment
        binding.btnFavorite.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.ll_fragment, FavoriteFragment()).commit()
        }
    }

    //뒤로가기 눌렀을 경우 두번 눌러야 꺼지게 설정
    override fun onBackPressed() {
        if(System.currentTimeMillis() - backClickTime >=2000){
            Toast.makeText(this,"한번 더 눌러주세요. 2초안에 누르셔야합니다.", Toast.LENGTH_SHORT).show()
            backClickTime = System.currentTimeMillis()
        }
        else{
            super.onBackPressed()   //else문안에 있어야 제대로 작동함.
            Toast.makeText(this,"종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        const val PARAM_USER = "param_user"
    }
}