package org.techtown.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.techtown.presentation.base.BaseViewModel
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.util.Const

class SplashViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val searchedUserPublishSubject: PublishSubject<List<UserModel>> =
        PublishSubject.create()

    fun searchUser() {
        userRepository.getUserInfo("hello", 1, Const.PER_PAGE_LIST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchedUserList ->
                searchedUserPublishSubject.onNext(searchedUserList.body()?.items)
            }, {
                searchedUserPublishSubject.onError(Throwable("유저 검색중 문제가 생김."))
            }).addTo(compositeDisposable)
    }
}