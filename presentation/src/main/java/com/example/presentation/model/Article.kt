package com.example.presentation.model

//뉴스 기사용 데이터 모델
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)