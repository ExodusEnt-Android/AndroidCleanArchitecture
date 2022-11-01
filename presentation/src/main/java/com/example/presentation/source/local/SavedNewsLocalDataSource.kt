package com.example.presentation.source.local

import com.example.presentation.model.Article

/**
 * Date: 2022/10/30
 * Author: idonghun
 *
 * Content: 로컬 io 코드가 들어가는   로컬 datasource interface 이다.
 *
 * @see getSavedArticleList 로컬 룸에 저장한  게시글 리스트를 가져온다.
 * @see saveArticle 로컬 룸에 게시글을 저장한다.
 * @see removeArticle 로컬 룸에 게시글을 삭제한다.
 *
 * **/
interface SavedNewsLocalDataSource {
    fun getSavedArticleList(callback: (List<Article>?, Throwable?) -> Unit)
    fun saveArticle(article: Article, callback:()->Unit)
    fun removeArticle(article: Article, callback:()->Unit)
}