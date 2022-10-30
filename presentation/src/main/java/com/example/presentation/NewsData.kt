package com.example.presentation

data class NewsData(
    var articles: ArrayList<Items>
)

data class Items(
    var author : String,
    var title : String,
    var description : String,
    var urlToImage : String,

)