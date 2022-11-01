package org.techtown.presentation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.techtown.presentation.model.Articles

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles() : List<Articles>

    @Insert(onConflict = REPLACE)
    fun insertArticle(articles: Articles)

    @Query("DELETE FROM articles WHERE url =:url")
    fun deleteArticle(url: String)
}