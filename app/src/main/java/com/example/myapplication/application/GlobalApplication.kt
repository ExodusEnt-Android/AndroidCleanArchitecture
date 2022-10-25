package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.BuildConfig
import com.example.myapplication.timber.TimberCustomTree
import timber.log.Timber

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberCustomTree())
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}