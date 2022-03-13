package org.techtown.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.techtown.presentation.base.BaseViewModel
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.util.Const
import java.util.concurrent.TimeUnit

class SplashViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val searchedUserPublishSubject: PublishSubject<List<UserModel>> =
        PublishSubject.create()

    fun searchUser() {
        Single.zip(
            userRepository.getUserInfo("hello", 1, Const.PER_PAGE_LIST)
                .subscribeOn(Schedulers.io()).map {
                    if (it.isSuccessful) {
                        it.body()
                    } else {
                        throw Throwable()
                    }
                }.retryWhen { errorObservable ->
                    errorObservable
                        .delay(3, TimeUnit.SECONDS)
                        .take(2)
                }.observeOn(AndroidSchedulers.mainThread()),
            Single.timer(2, TimeUnit.SECONDS), { dataModelSearchedUsers, time ->
                searchedUserPublishSubject.onNext(dataModelSearchedUsers?.items)
            }).onErrorReturn {
            searchedUserPublishSubject.onError(Throwable("유저정보를 가져올수가 없습니다."))
        }.subscribe().addTo(compositeDisposable)
    }
}