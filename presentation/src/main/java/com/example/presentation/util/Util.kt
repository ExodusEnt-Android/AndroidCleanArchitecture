package com.example.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object Util {

    //시간 얼마나 지났는지를 체크
    fun String.checkTimePassed():String{

        val transFormat =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = transFormat.parse(this)

        val diff = (Date().time - date.time) / 1000
        return when (diff) {
            in 0 until 10 -> "방금 전"
            in 10 until 60 -> "$diff 초 전"
            in 60 until 60 * 60 -> "${diff/60} 분 전"
            in 60 * 60 until 60 * 60 * 24 -> "${diff/3600} 시간 전"
            else -> this.toString()
        }
    }

}