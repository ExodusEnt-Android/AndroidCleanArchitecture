package org.techtown.androidcleanarchitecturecoroutine

import android.app.Application
import org.techtown.util.preference.PreferenceUtil
import timber.log.Timber


/**
 * @see
 * */

class GlobalApplication : Application() {

    override fun onCreate() {
        PreferenceUtil.with(application = this)
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}