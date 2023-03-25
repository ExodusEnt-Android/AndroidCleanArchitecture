/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.viewModel

import androidx.lifecycle.*
import com.example.data.repository.NewsRepository
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.fromData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TopNewsViewModel @Inject constructor (
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _articleList = MutableLiveData<List<PresentationArticles>>()
    val articleList: LiveData<List<PresentationArticles>> = _articleList

    private var tempArticleList : List<PresentationArticles> = emptyList()

    init {
        topNews()
    }
    fun topNews() {
        viewModelScope.launch {
            newsRepository.getNews("us", null).collect{ it ->
                tempArticleList = it.dataArticlesModel.map { it.fromData() }
                _articleList.value = tempArticleList
            }
        }
    }

}