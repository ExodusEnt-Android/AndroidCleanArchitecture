package org.techtown.presentation.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MyGson {

    companion object{
        private val REMOTE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

        fun getInstance():Gson{
            val builder = GsonBuilder()
            builder.setDateFormat(REMOTE_DATE_FORMAT).setPrettyPrinting().create()
            return builder.create()
        }
    }
}