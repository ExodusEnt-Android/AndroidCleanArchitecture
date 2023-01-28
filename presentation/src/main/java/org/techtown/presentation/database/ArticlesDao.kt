package org.techtown.presentation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.techtown.presentation.model.Articles

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<Articles>

    @Insert(onConflict = REPLACE)
    suspend fun insertArticle(articles: Articles)

    @Query("DELETE FROM articles WHERE url =:url")
    suspend fun deleteArticle(url: String)
}