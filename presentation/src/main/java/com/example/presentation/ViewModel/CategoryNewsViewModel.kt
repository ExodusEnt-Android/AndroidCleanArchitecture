/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.viewModel

import androidx.lifecycle.*
import com.example.domain.repository.NewsRepository
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.fromData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CategoryNewsViewModel @Inject constructor (
    private val newsRepository: com.example.domain.repository.NewsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _articleList = MutableLiveData<List<PresentationArticles>>()
    var articleList : LiveData<List<PresentationArticles>> = _articleList

    private var tempArticleList : List<PresentationArticles> = emptyList()

    private var category: String = savedStateHandle.get<String>("category") ?: ""

    init {
        newsCategory()

        savedStateHandle.get<String>("category")?.run {
            category = this
        }
    }

    private fun newsCategory() {
        viewModelScope.launch {
            newsRepository.getNews("us",category).collect{
                tempArticleList = it.dataArticlesModelDataModel.map { it.fromData() }
                _articleList.value = tempArticleList
            }
        }
    }

}