package org.techtown.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtown.data.repository.news.NewsRepository
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.Articles.Companion.fromData

class SavedViewModel(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _savedArticleList = MutableLiveData<ArrayList<Articles>>()
    val savedArticleList: LiveData<ArrayList<Articles>> = _savedArticleList

    private var shouldRequestViewMore: Boolean = true

    private var tempSavedArticleList: ArrayList<Articles> = arrayListOf()
        set(value) {
            savedStateHandle["saved_article_list"] = value
            field = value
        }

    init {
        savedStateHandle.get<ArrayList<Articles>>("saved_article_list")?.run {
            tempSavedArticleList = this
        }
        getSavedArticleList()
    }

    private fun getSavedArticleList() {

        if (shouldRequestViewMore) {
            viewModelScope.launch {
                newsRepository.getAllArticles().
                    map { savedArticles->
                        savedArticles.map { it.fromData() }
                    }.
                collect { presentArticles ->
                    if (presentArticles.isNotEmpty()) {
                        tempSavedArticleList.addAll(presentArticles)
                        _savedArticleList.value = tempSavedArticleList
                    } else {
                        shouldRequestViewMore = false
                    }
                }
            }
        }

    }
}