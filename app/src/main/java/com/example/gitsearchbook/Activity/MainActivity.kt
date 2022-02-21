package com.example.gitsearchbook.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

    private lateinit var gitSearchAdapter: UserFragmentAdapter
    private lateinit var btnUser : Button
    private lateinit var btnFavorite : Button

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