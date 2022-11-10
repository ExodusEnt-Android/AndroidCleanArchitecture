package com.example.presentation.source.local

import android.app.Activity
import com.example.presentation.model.Article
import com.example.presentation.room.LocalDataBase

/**
 * Author: idonghun
 *
 * Content: 저장한 게시글 가져오는  datasource의  실제  구현체 부분
 *
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

    override fun saveArticle(article: Article, callback: () -> Unit) {
        val r = Runnable {
            localDataBase.runInTransaction {
                localDataBase.getNewsArticleDao().setSavedArticle(article)
                activity.runOnUiThread {
                    callback.invoke()
                }
            }
        }
        val thread = Thread(r)
        thread.start()
    }

    override fun removeArticle(article: Article, callback: () -> Unit) {
        val r = Runnable {
           localDataBase.runInTransaction {
                localDataBase.getNewsArticleDao().deleteSavedArticle(
                    publishedAt = article.publishedAt.toString(),
                    title = article.title.toString(),
                    url = article.url.toString()
                )
                activity.runOnUiThread {
                    callback.invoke()
                }
            }
        }
        val thread = Thread(r)
        thread.start()
    }
}