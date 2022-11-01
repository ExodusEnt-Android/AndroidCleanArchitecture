package org.techtown.presentation.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

class Logger private constructor(){
    companion object{
        var isDebugMode:Boolean = false//디버그 모드 인지 여부

        fun debugModeCheck(context: Context) {
            isDebugMode = context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        }

        private const val DEFAULT_TAG = "VALUE_CHECK"//tag 설정 안했을때  default로 들어가는 태그

        //일반 태그용
        fun v(msg: String) = logger(Log.VERBOSE, msg)
        fun d(msg: String) = logger(Log.DEBUG, msg)
        fun i(msg: String) = logger(Log.INFO, msg)
        fun w(msg: String) = logger(Log.WARN, msg)
        fun e(msg: String) = logger(Log.ERROR, msg)

        //태그 커스텀 용 -> 기존 idol_debugging 태그가 아닌  일반  지정한 태그로  확인 가능
        fun v(tag: String, msg: String) = logger(Log.VERBOSE, tag, msg)
        fun d(tag: String, msg: String) = logger(Log.DEBUG, tag, msg)
        fun i(tag: String, msg: String) = logger(Log.INFO, tag, msg)
        fun w(tag: String, msg: String) = logger(Log.WARN, tag, msg)
        fun e(tag: String, msg: String) = logger(Log.ERROR, tag, msg)

        //defualt   태그용
        private fun logger(priority: Int, msg: String) {

            //debug 가능한 상태일때는  log를 출력한다.
            //해당 클래스 이름  메소드 이름 파일이름 및  라인 넘버  출력하게 함.
            if (isDebugMode) {
                val message =
                    "[${Thread.currentThread().stackTrace[4].fileName} => "+
                        "${Thread.currentThread().stackTrace[4].methodName}()] :: $msg" +
                        "(${Thread.currentThread().stackTrace[4].fileName}:${Thread.currentThread().stackTrace[4].lineNumber})"
                Log.println(priority, DEFAULT_TAG, message)
            }
        }

        //커스텀  태그용
        private fun logger(priority: Int, tag: String ,msg: String) {

            //debug 가능한 상태일때는  log를 출력한다.
            //해당 클래스 이름  메소드 이름 파일이름 및  라인 넘버  출력하게 함.
            if (isDebugMode) {
                val message =
                    "[${Thread.currentThread().stackTrace[4].fileName} => "+
                        "${Thread.currentThread().stackTrace[4].methodName}()] :: $msg" +
                        "(${Thread.currentThread().stackTrace[4].fileName}:${Thread.currentThread().stackTrace[4].lineNumber})"
                Log.println(priority, tag, message)
            }
        }


        //태그 모음
        const val API_RESPONSE_TAG = "API_RESPONSE" //api response 값 체크용  tag
        const val VALUE_CHECK_TAG = "VALUE_CHECK" //값 체크용  tag
    }
}