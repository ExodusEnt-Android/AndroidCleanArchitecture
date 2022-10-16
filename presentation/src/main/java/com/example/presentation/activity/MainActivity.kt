package com.example.presentation.activity

import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun ActivityMainBinding.onCreate() {
        Timber.v("asdasdasd ")
    }

}
