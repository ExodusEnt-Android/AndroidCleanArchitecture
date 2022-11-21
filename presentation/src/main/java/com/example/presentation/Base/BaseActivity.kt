package com.example.presentation.Base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity<VB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : AppCompatActivity() {

    lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutRes)
        mBinding.onCreate()
    }

    open fun VB.onCreate() = Unit
}