package com.example.presentation.source.local

import com.example.presentation.Articles

class SavedNewsLocalDataSourceImpl : SavedNewsLocalDataSource {
    override fun getSavedNews(callback: (List<Articles>?, Throwable?) -> Unit) {

    }

    override fun saveNews(articles: Articles, callback: () -> Unit) {

    }

    override fun deleteNews(url: String, callback: () -> Unit) {

    }
}