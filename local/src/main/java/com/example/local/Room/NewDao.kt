package com.example.local.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Articles
import com.example.local.model.LocalArticles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items : LocalArticles)

    @Query("SELECT * FROM articles")
    suspend fun getAll() : List<LocalArticles>

    @Query("DELETE FROM articles WHERE url =:url")
    suspend fun deleteArticle(url : String)

}