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
        set(value) {
            savedStateHandle["category_article_list"] = value
            field = value
        }

    private var category: String = savedStateHandle.get<String>("category_detail") ?: ""
        set(value) {
            savedStateHandle["category_detail"] = value
            field = value
        }


    private var offset = 1
    private var limit = 5

    init {
        savedStateHandle.get<ArrayList<Articles>>("category_article_list")?.run {
            tempCategoryList = this
        }
        savedStateHandle.get<String>("category_detail")?.run {
            category = this
        }
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
                        _categoryArticleList.postValue(tempCategoryList)

                        offset += 1
                    } else {
                        shouldRequestViewMore = false
                    }
                }
            }
        }
    }

}