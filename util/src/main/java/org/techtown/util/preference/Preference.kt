package org.techtown.util.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object PreferenceUtil {

    private lateinit var prefs: SharedPreferences

    private const val PREFERENCE_FILE_NAME = "prefs_name"

    fun with(application: Application) {
        prefs = application.getSharedPreferences(
            PREFERENCE_FILE_NAME, Context.MODE_PRIVATE
        )
    }

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun setBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }
}