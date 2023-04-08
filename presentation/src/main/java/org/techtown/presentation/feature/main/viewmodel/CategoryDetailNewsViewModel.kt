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
class CategoryDetailNewsViewModel @Inject constructor(
    private val getRemoteNewsUseCase: GetRemoteNewsUseCase,
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
                getRemoteNewsUseCase(
                    "us",
                    category = category,
                    limit,
                    offset
                ).map { data ->
                    data.fromData()
                }.collect { presentArticles ->

                    if (presentArticles.articles.isNotEmpty()) {
                        tempCategoryList.addAll(presentArticles.articles)
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