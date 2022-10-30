package com.example.presentation.source.local

import com.example.presentation.model.Article

/**
 * Date: 2022/10/30
 * Author: idonghun
 *
 * Content: 로컬 io 코드가 들어가는   로컬 datasource interface 이다.
 *
 * @see getSavedArticleList 로컬 룸에 저장한  게시글 리스트를 가져온다.
 *
 * **/
interface SavedNewsLocalDataSource {
    fun getSavedArticleList(callback: (List<Article>?, Throwable?) -> Unit)
}