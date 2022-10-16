package com.example.myapplication.application

import android.app.Application

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}