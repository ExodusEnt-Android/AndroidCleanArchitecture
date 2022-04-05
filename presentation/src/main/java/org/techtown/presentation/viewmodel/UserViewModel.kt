package org.techtown.presentation.viewmodel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import org.techtown.presentation.base.BaseViewModel
import org.techtown.data.repository.UserRepository
import org.techtown.presentation.model.PresentationUserModel
import org.techtown.presentation.model.PresentationUserModel.Companion.toDataModel
import org.techtown.presentation.model.PresentationUserModel.Companion.toPresentationModel
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class UserViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val behaviorSubject = BehaviorSubject.createDefault(0L)

    val mainBackPressPublishSubject: PublishSubject<Boolean> = PublishSubject.create()

    val userFragmentUpdateUserList: PublishSubject<List<PresentationUserModel>> = PublishSubject.create()
    var searchedUserList: MutableList<PresentationUserModel> = mutableListOf()

    val favoritesFragmentUpdateUserList: PublishSubject<List<PresentationUserModel>> = PublishSubject.create()
    val favoriteUserList: MutableList<PresentationUserModel> = mutableListOf()

    fun clickBackButton() {
        behaviorSubject.toFlowable(BackpressureStrategy.BUFFER)
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(2, 1)
            .map { Pair(it[0], it[1]) }
            .map { it.second - it.first < TimeUnit.SECONDS.toMillis(2) }
            .subscribe { isFinish ->
                if (isFinish) {
                    mainBackPressPublishSubject.onNext(true)
                } else {
                    mainBackPressPublishSubject.onNext(false)
                }
            }.addTo(compositeDisposable)
    }

    fun setSearchUserList(searchedUserList: List<PresentationUserModel>) {
        this.searchedUserList = searchedUserList as MutableList<PresentationUserModel>
    }

    fun searchUser(query: String?, currentPage: Int, perPage: Int, isSearch: Boolean) {

        Single.zip(
            userRepository.getUserInfo(query, currentPage, perPage)
                .subscribeOn(Schedulers.io())
                .retryWhen { errors ->
                    var counter = AtomicInteger()
                    errors.takeWhile {
                        counter.getAndIncrement() < 3
                    }.flatMap {
                        Flowable.timer(3, TimeUnit.SECONDS)
                    }
                },
            userRepository.getFavUserInfo(true), { remote, local ->

                //검색했을경우 계속 추가해주는게아니고 새로운 리스트를 넣어줍니다.
                if (isSearch)
                    searchedUserList.clear()

                remote.body()?.items.let { dataModelSearchUserList ->
                    if (!dataModelSearchUserList.isNullOrEmpty()) {
                        val presentationSearchUserList = dataModelSearchUserList.map { toPresentationModel(it) }
                        searchedUserList.addAll(presentationSearchUserList)
                    }

                    searchedUserList.map { searchedUser ->
                        if (local.any { it.id == searchedUser.id }) {
                            searchedUser.is_favorite = true
                        }
                    }

                }
                return@zip searchedUserList.map { it.copy() }

            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ searchedUserList ->
                userFragmentUpdateUserList.onNext(searchedUserList)

            }, {
                userFragmentUpdateUserList.onError(Throwable("유저 검색중 문제가 생김."))

            }).addTo(compositeDisposable)
    }

    fun initialListSetting() {
        userRepository.getFavUserInfo(true)
            ?.subscribeOn(Schedulers.io())
            ?.map { dataModelFavoriteUsers ->

                val newList = searchedUserList.map { it.copy() }
                newList.map { searchedUserList ->
                    searchedUserList.is_favorite =
                        dataModelFavoriteUsers.any { it.id == searchedUserList.id }
                }

                favoriteUserList.addAll(dataModelFavoriteUsers.map { toPresentationModel(it) })
                return@map newList
            }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                userFragmentUpdateUserList.onNext(it?.toMutableList())
            }, {
                userFragmentUpdateUserList.onError(Throwable("즐겨찾기 업데이트중 문제가 생김."))
            })?.addTo(compositeDisposable)
    }

    fun setFavUser(model: PresentationUserModel) {
        //즐겨찾기 추가.
        userRepository.setFavUserInfo(toDataModel(model))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError {
                userFragmentUpdateUserList.onError(Throwable("즐겨찾기 유저 추가하는 중 문제가 생김."))
            }?.doOnComplete {

                favoriteUserList.add(model)
                favoritesFragmentUpdateUserList.onNext(favoriteUserList)

                searchedUserList.find {
                    it.id == model.id
                }?.is_favorite = true

                userFragmentUpdateUserList.onNext(searchedUserList.map { it.copy() })
            }
            ?.subscribe()?.addTo(compositeDisposable)
    }

    fun deleteFavUser(model: PresentationUserModel) {
        userRepository.deleteFavUserInfo(toDataModel(model))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError {
                userFragmentUpdateUserList.onError(Throwable("즐겨찾기 유저 삭제 중 문제가 생김."))
            }?.doOnComplete {

                favoriteUserList.removeAll { it.id == model.id }
                favoritesFragmentUpdateUserList.onNext(favoriteUserList)

                searchedUserList.find {
                    it.id == model.id
                }?.is_favorite = false

                userFragmentUpdateUserList.onNext(searchedUserList.map { it.copy() })
            }
            ?.subscribe()?.addTo(compositeDisposable)
    }
}