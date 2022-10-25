package com.example.presentation.util

import android.content.Context
import com.google.gson.Gson

class PreferenceManager {
    companion object {

        // SharedPreference에 키/값을 설정
        fun setPreference(context: Context, key: String, value: Any) {
            try {
                val preferences =
                    context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                when (value) {
                    is String -> editor.putString(key, value)//value값 string 일떄
                    is Int -> editor.putInt(key, value)//value값 int일때
                    is Boolean -> editor.putBoolean(key, value)//value값 boolean일떄
                    is Long -> editor.putLong(key, value)//value값 long 일때
                    is Float -> editor.putFloat(key, value)//value값 float일때
                    is List<*> -> {//value값 list형 일때  gson으로  Json형태 string으로 변경후 저장
                        val gson = Gson()
                        val listToString = gson.toJson(value)
                        editor.putString(key, listToString)
                    }
                }
                editor.apply()//shared 저장
            } catch (e: Exception) {//혹시 몰라 예외처리
                e.printStackTrace()
            }
        }

        //preference 가져오기
        fun getPreference(
            context: Context,
            key: String,
            defaultValue: Any?
        ): Any? {
            return try {
                val preferences =
                    context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                when (defaultValue) {
                    is String -> preferences.getString(key, defaultValue)
                    is Int -> preferences.getInt(key, defaultValue)
                    is Boolean -> preferences.getBoolean(key, defaultValue)
                    is Long -> preferences.getLong(key, defaultValue)
                    is Float -> preferences.getFloat(key, defaultValue)
                    is List<*> -> preferences.getString(key, "")
                    else -> preferences.getString(key, "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                defaultValue//exception 뜰 경우 default value return
            }
        }


        //preference 없애기
        fun removePreference(context: Context, key: String) {
            try {
                val preferences =
                    context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.remove(key)
                editor.apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //preference 모두 없애기
        fun removeAllPreference(context: Context) {
            try {
                val preferences =
                    context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.clear()//전체 preference value clear
                editor.apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //해당 preference key값 있는지 체크
        fun isPreferenceExist(context: Context, key: String): Boolean {
            return try {
                val preferences =
                    context.getSharedPreferences("pref", Context.MODE_PRIVATE)
                preferences.contains(key)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }


    }
}