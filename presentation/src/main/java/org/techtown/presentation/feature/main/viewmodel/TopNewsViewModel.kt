package org.techtown.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.techtown.data.repository.news.NewsRepository
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel.Companion.fromData


class TopNewsViewModel(
    private val newsRepository: NewsRepository,
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
                newsRepository.getTopHeadlinesArticles(
                    country = "us", pageSize = limit, offset = offset, category = null
                ).map { data ->
                    data.fromData()
                }.collect { data ->
                    if (data.articles.isNotEmpty()) {
                        tempArticleList.addAll(data.articles)
                        _articleList.postValue(tempArticleList)

                        offset += 1
                    } else {
                        shouldRequestViewMore = false
                    }
                }
            }
        }

    }

}