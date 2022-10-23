package com.example.presentation.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.presentation.model.Article

@Dao
interface NewsArticleDao {
    @Insert(onConflict = REPLACE)
    fun setSavedArticle(article: Article)

    @Query("SELECT * FROM newsArticleTable")
    fun loadSavedNewsArticles():List<Article>

    @Query("DELETE FROM newsArticleTable WHERE uid = :uid")
    fun deleteSavedArticle(uid:Long)
}