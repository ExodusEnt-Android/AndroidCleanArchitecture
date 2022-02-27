package com.example.gitsearchbook.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.gitsearchbook.Fragment.FavoriteFragment
import com.example.gitsearchbook.R
import com.example.gitsearchbook.Fragment.UserFragment
import com.example.gitsearchbook.Adapter.UserFragmentAdapter
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import com.google.gson.JsonArray

import com.example.gitsearchbook.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger


class MainActivity : AppCompatActivity(){

    private lateinit var btnUser : Button
    private lateinit var btnFavorite : Button
    private var backClickTime : Long = 0    //뒤로가기 시간 재기 위함.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/") //어떤 서버로 네트워크 통신을 요청할 것인지에 대한 설정
            .addConverterFactory(GsonConverterFactory.create()) //통신이 완료된 후, 어떤 Converter를 이용하여 데이터를 파싱할 것인지에 대한 설정
            .build()

        btnUser = findViewById(R.id.btn_user)
        btnFavorite = findViewById(R.id.btn_favorite)

        btnUser.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.ll_fragment, UserFragment()).commit()
        }
        btnFavorite.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.ll_fragment, FavoriteFragment()).commit()

        }

    }

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