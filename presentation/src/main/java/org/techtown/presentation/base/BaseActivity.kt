package org.techtown.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseActivity<VDB : ViewDataBinding>(@LayoutRes val layoutRes: Int) :
    AppCompatActivity() {

    lateinit var binding: VDB

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()
    }

    open fun VDB.onCreate() = Unit

    override fun onDestroy() {
        super.onDestroy()

        //화면 destroy될때  disposable들 모두 clear -> 메모리 누수 방지
        compositeDisposable.dispose()
    }
}