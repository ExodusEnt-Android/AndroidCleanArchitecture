package com.example.presentation.source.local

import android.app.Activity
import com.example.presentation.model.Article
import com.example.presentation.room.LocalDataBase

/**
 * Date: 2022/10/30
 * Author: idonghun
 *
 * Content: 저장한 게시글 가져오는  datasource의  실제  구현체 부분
 *
 * @see getSavedArticleList 저장한 게시글 리스트를 가져온다.
 * **/
class SavedNewsLocalDataSourceImpl(
    private val localDataBase: LocalDataBase,
    private val activity: Activity
) : SavedNewsLocalDataSource {
    override fun getSavedArticleList(callback: (List<Article>?, Throwable?) -> Unit) {
        val r = Runnable {
            localDataBase.runInTransaction {
                val list = localDataBase.getNewsArticleDao().loadSavedNewsArticles()
                activity.runOnUiThread {
                    try {
                        callback.invoke(list, null)
                    }catch (e:java.lang.Exception){
                       callback.invoke(null,e)
                    }
                }
            }
        }
        val thread = Thread(r)
        thread.start()
    }
}