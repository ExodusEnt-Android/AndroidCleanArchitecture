package org.techtown.presentation.feature.main.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import org.techtown.data.repository.news.NewsRepository
import org.techtown.presentation.feature.main.viewmodel.CategoryDetailNewsViewModel
import org.techtown.presentation.feature.main.viewmodel.NewsDetailViewmodel
import org.techtown.presentation.feature.main.viewmodel.SavedViewModel
import org.techtown.presentation.feature.main.viewmodel.TopNewsViewModel

class ViewModelFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(TopNewsViewModel::class.java) -> {
                TopNewsViewModel(newsRepository, extras.createSavedStateHandle()) as T
            }
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> {
                SavedViewModel(newsRepository, extras.createSavedStateHandle()) as T
            }
            modelClass.isAssignableFrom(CategoryDetailNewsViewModel::class.java) -> {
                CategoryDetailNewsViewModel(newsRepository, extras.createSavedStateHandle()) as T
            }
            modelClass.isAssignableFrom(NewsDetailViewmodel::class.java) -> {
                NewsDetailViewmodel(newsRepository, extras.createSavedStateHandle()) as T
            }
            else -> {
                throw Exception("can not create ViewModel")
            }
        }
    }
}
