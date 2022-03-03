package com.example.gitsearchbook.Fragment

import com.example.gitsearchbook.GitRepoService
import com.example.gitsearchbook.GitUserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitRetrofit {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/") //어떤 서버로 네트워크 통신을 요청할 것인지에 대한 설정
        .addConverterFactory(GsonConverterFactory.create()) //통신이 완료된 후, 어떤 Converter를 이용하여 데이터를 파싱할 것인지에 대한 설정
        .build()

    val repoService: GitRepoService = retrofit.create(GitRepoService::class.java)
    val userService : GitUserService = retrofit.create(GitUserService::class.java)
}