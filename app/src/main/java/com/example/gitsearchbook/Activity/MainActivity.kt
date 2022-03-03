package com.example.gitsearchbook.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.gitsearchbook.Fragment.FavoriteFragment
import com.example.gitsearchbook.R
import com.example.gitsearchbook.Fragment.UserDetailFragment


class MainActivity : AppCompatActivity(){

    private lateinit var btnUser : Button
    private lateinit var btnFavorite : Button
    private var backClickTime : Long = 0    //뒤로가기 시간 재기 위함.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnUser = findViewById(R.id.btn_user)   //view binding으로 바꾸기(1주차) -> 2주차에 data binding
        btnFavorite = findViewById(R.id.btn_favorite)

        btnUser.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.ll_fragment, UserDetailFragment()).commit()
        }
        btnFavorite.setOnClickListener {
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

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}