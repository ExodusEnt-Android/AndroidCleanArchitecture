package org.techtown.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import org.techtown.presentation.model.UserModel
import org.techtown.presentation.repository.UserRepository
import org.techtown.presentation.util.Const

class SplashViewModel(private val userRepository: UserRepository) : ViewModel() {

    val searchedUserPublishSubject: PublishSubject<List<UserModel>> =
        PublishSubject.create()


    //여기서도 null체크 번거로움 제거.
    private val compositeDisposable = CompositeDisposable()

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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}