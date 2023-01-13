package com.example.presentation

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.ClassCastException
import java.lang.NullPointerException
import java.util.ArrayList

public class PreferenceUtil {
    companion object{
        private val PREF_NAME = "com.example.presentation"

        fun setPreferenceArray(context: Context, key: String?, value: ArrayList<*>?) {
            val settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = settings.edit()
            val gson = Gson()
            val listValue = gson.toJson(value)
            editor.putString(key, listValue)
            editor.apply()
        }

        // SharedPreference에 키/값을 설정
        fun setPreference(context: Context, key: String?, value: String) {
            val settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = settings.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun setPreference(context: Context, key: String?, value: Boolean) {
            val settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = settings.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        // SharedPreference에 키/값을 설정
        @Throws(NullPointerException::class)
        fun getPreference(context: Context, key: String?): String? {
            val settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return settings.getString(key, "")
        }

        // SharedPreference에 키/값을 설정
        fun getPreferenceBool(context: Context, key: String?, def_value: Boolean): Boolean {
            val settings =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            var ret = def_value
            try {
                ret = settings.getBoolean(key, def_value)
            } catch (e: ClassCastException) {
                settings.edit().putBoolean(key, def_value)
            }
            return ret
        }
    }
}