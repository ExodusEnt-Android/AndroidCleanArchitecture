package org.techtown.local.feature.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.techtown.local.feature.model.LocalArticles

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<LocalArticles>

    @Insert(onConflict = REPLACE)
    suspend fun insertArticle(articles: LocalArticles)

    @Query("DELETE FROM articles WHERE url =:url")
    suspend fun deleteArticle(url: String)
}