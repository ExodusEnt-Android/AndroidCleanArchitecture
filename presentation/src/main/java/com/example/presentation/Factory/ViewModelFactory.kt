/**
 * Copyright (C) 2023. ExodusEnt Corp. All rights reserved.
 * You must have prior written permission to read this file.
 * @author Kim min gyu <mingue0605@myloveidol.com>
 * Description:
 *
 * */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.data.repository.NewsRepository
import com.example.presentation.viewModel.CategoryNewsViewModel
import com.example.presentation.viewModel.NewsDetailViewModel
import com.example.presentation.viewModel.SavedViewModel
import com.example.presentation.viewModel.TopNewsViewModel


class ViewModelFactory(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(TopNewsViewModel::class.java) -> {
                TopNewsViewModel(repository, extras.createSavedStateHandle()) as T
            }
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> {
                SavedViewModel(repository, extras.createSavedStateHandle()) as T
            }

            modelClass.isAssignableFrom(NewsDetailViewModel::class.java) -> {
                NewsDetailViewModel(repository, extras.createSavedStateHandle()) as T
            }
            modelClass.isAssignableFrom(CategoryNewsViewModel::class.java) -> {
                CategoryNewsViewModel(repository,extras.createSavedStateHandle()) as T
            }
            else -> {
                throw Exception("cannot create viewModel")
            }
        }

    }
}