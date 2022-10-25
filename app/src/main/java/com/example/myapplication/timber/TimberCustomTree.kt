package com.example.myapplication.timber

import timber.log.Timber

class TimberCustomTree: Timber.DebugTree(){
    override fun createStackElementTag(element: StackTraceElement): String {
        return "timber-message ${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
