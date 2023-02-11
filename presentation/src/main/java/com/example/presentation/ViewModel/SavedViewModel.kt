/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

package com.example.presentation.ViewModel

import androidx.lifecycle.*
import com.example.data.repository.NewsRepository
import com.example.presentation.model.PresentationArticles
import com.example.presentation.model.PresentationArticles.Companion.fromData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SavedViewModel (
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _articleList = MutableLiveData<List<PresentationArticles>>()
    var articleList : LiveData<List<PresentationArticles>> = _articleList

    private var tempArticleList : List<PresentationArticles> = emptyList()

    //저장된 뉴스 id를 통해 보여주기
    fun newsSource() {
        viewModelScope.launch {
            newsRepository.getAll().collect{ it ->
                tempArticleList = it.map { it.fromData() }
                _articleList.value = tempArticleList
            }
        }
    }
}