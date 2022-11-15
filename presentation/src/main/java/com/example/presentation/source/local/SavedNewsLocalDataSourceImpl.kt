package com.example.presentation.source.local

import android.app.Activity
import android.content.Context
import com.example.presentation.Articles
import com.example.presentation.Room.AppDB

class SavedNewsLocalDataSourceImpl(
    private val activity: Activity
) : SavedNewsLocalDataSource {
    override fun getSavedNews(callback: (List<Articles>?, Throwable?) -> Unit) {    //저장된 데이터 불러오기
        val thread = Thread{
            val newsModels = AppDB.getInstance(activity)?.newsDao()?.getAll()
            activity.runOnUiThread {
                callback.invoke(newsModels, null)
            }
        }
        thread.start()
    }

    override fun saveNews(articles: Articles, callback: () -> Unit) {   //데이터 저장하기
        val thread = Thread{
            AppDB.getInstance(activity)?.newsDao()?.insert(articles)
            activity.runOnUiThread {
                callback.invoke()
            }
        }
        thread.start()
    }

    override fun deleteNews(url: String, callback: () -> Unit) {
        val thread = Thread{
            AppDB.getInstance(activity)?.newsDao()?.deleteArticle(url)
            activity.runOnUiThread {
                callback.invoke()
            }
        }
        thread.start()
    }
}