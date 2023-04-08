package org.techtown.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetRemoteNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel.Companion.fromData
import javax.inject.Inject


@HiltViewModel
class TopNewsViewModel @Inject constructor(
    private val getRemoteNewsUseCase: GetRemoteNewsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _articleList = MutableLiveData<ArrayList<Articles>>()
    val articleList: LiveData<ArrayList<Articles>> = _articleList

    private var shouldRequestViewMore: Boolean = true

    private var tempArticleList: ArrayList<Articles> = arrayListOf()

    private var offset = 1
    private var limit = 5

    init {
        getArticles()
    }

    fun getArticles() {
        if (shouldRequestViewMore) {
            viewModelScope.launch {
                getRemoteNewsUseCase.invoke(
                    country = "us", pageSize = limit, offset = offset, category = null
                ).map { data ->
                    data.fromData()
                }.collect { presentArticles ->
                    if (presentArticles.articles.isNotEmpty()) {
                        tempArticleList.addAll(presentArticles.articles)
                        _articleList.value = tempArticleList

                        offset += 1
                    } else {
                        shouldRequestViewMore = false
                    }
                }
            }
        }

    }

}