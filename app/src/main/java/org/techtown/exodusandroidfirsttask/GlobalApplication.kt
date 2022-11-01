package org.techtown.exodusandroidfirsttask

import android.app.Application
import com.amitshekhar.DebugDB
import dagger.hilt.android.HiltAndroidApp
import org.techtown.presentation.logger.Logger

@HiltAndroidApp
class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.debugModeCheck(this)//로거 클래스의 debug가능 여부를 체크해준다.
        DebugDB.getAddressLog();
    }
}