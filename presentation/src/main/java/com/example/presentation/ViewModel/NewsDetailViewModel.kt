/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.ViewModel

import android.os.Parcelable
import androidx.lifecycle.*
import com.example.data.model.Articles
import com.example.data.repository.NewsRepository
import com.example.presentation.R
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.toData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsDetailViewModel (
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _isSaved = MutableLiveData<Boolean>()
    var isSaved : LiveData<Boolean> = _isSaved

    private var articles: PresentationArticles = savedStateHandle.get<PresentationArticles>("items")!!

    init {
        savedStateHandle.get<PresentationArticles>("items")?.run {
            articles = this
        }
        newsDetail()
    }

    private fun newsDetail(){
        viewModelScope.launch {
            newsRepository.getAll().collect{ it ->
                _isSaved.value = it.any{it.url == articles.url}
            }
        }
    }

    fun deleteArticle(){
        viewModelScope.launch {
            _isSaved.value = false
            newsRepository.deleteArticle(articles.url)
        }
    }

    fun saveArticle(){
        viewModelScope.launch {
            _isSaved.value = true
            newsRepository.insert(articles.toData())
        }
    }
}