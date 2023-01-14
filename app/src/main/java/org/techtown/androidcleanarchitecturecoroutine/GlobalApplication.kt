package org.techtown.androidcleanarchitecturecoroutine

import android.app.Application
import timber.log.Timber


/**
 * @see
 * */

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}