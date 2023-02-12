package org.techtown.presentation.feature.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techtown.data.repository.news.NewsRepository
import org.techtown.presentation.model.Articles
import org.techtown.presentation.model.Articles.Companion.toData

class NewsDetailViewmodel(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _initUI = MutableLiveData<Articles>()
    val initUI: LiveData<Articles> = _initUI

    private val _isSelected = MutableLiveData<Boolean>()
    val isSelected: LiveData<Boolean> = _isSelected

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean> = _isSaved

    private var articles = savedStateHandle.get<Articles>("top_news_detail") ?: Articles()

    init {

        //아티클 정보 초기 세팅값 넘겨줌.
        _initUI.postValue(articles)

        getAllArticles()
    }


    private fun getAllArticles() {
        viewModelScope.launch {
            newsRepository.getAllArticles().collect { data ->
                val isSelected = data.any { it.url == articles?.url }
                _isSelected.postValue(isSelected)
            }
        }
    }

    fun deleteArticle() {
        viewModelScope.launch {
            newsRepository.deleteArticle(articles?.url ?: return@launch)
            _isDeleted.postValue(true)
        }
    }

    fun insertArticle() {
        viewModelScope.launch {
            newsRepository.insertArticle(articles?.toData() ?: return@launch)
            _isSaved.postValue(true)
        }
    }
}