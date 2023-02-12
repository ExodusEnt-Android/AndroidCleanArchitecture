package org.techtown.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtown.data.repository.news.NewsRepository
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.NewsRootModel.Companion.fromData

class CategoryDetailNewsViewModel(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _categoryArticleList = MutableLiveData<ArrayList<Articles>>()
    val categoryArticleList: LiveData<ArrayList<Articles>> = _categoryArticleList

    private var shouldRequestViewMore: Boolean = true

    private var tempCategoryList: ArrayList<Articles> = arrayListOf()

    private var category: String = savedStateHandle.get<String>("category_detail") ?: ""

    private var offset = 1
    private var limit = 5

    init {
        getCategoryArticles()
    }

    fun getCategoryArticles() {
        if (shouldRequestViewMore) {
            viewModelScope.launch {
                newsRepository.getTopHeadlinesArticles(
                    "us",
                    category = category,
                    limit,
                    offset
                ).collect { data ->

                    if (data.fromData().articles.isNotEmpty()) {
                        tempCategoryList.addAll(data.fromData().articles)
                        _categoryArticleList.value = tempCategoryList

                        offset += 1
                    } else {
                        shouldRequestViewMore = false
                    }
                }
            }
        }
    }

}