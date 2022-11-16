package com.example.presentation.repository

import com.example.presentation.model.Article
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Date: 2022/10/30
 * Author: idonghun
 *
 * Content:  top new를 가져오는 레포지토리
 *
 * @see  getTopHeadLines  top news api에셔  paramter 값에 해당하는  게시글 리스트를 받아온다.
 * @see  getSavedArticleList 로컬 db 에  저장한  게시글 리스트를 받아온다.
 * @see  saveArticle 로컬 db 에  게시글을 저장한다.
 * @see  removeArticle 로컬 db 에  게시글을 삭제한다.
 * **/
interface TopNewsRepository {
    fun getTopHeadLines(
        category: String? = null,//optional
        page:Int,
        pageSize:Int
    ): Single<List<Article>>

    fun getSavedArticleList():Single<List<Article>>

    fun saveArticle(article: Article):Completable
    fun removeArticle(article: Article):Completable
}