package org.techtown.mymvvmtest

import android.app.Application
import com.amitshekhar.DebugDB
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            DebugDB.getAddressLog()
        }
    }
}