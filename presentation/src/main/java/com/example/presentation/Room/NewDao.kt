package com.example.presentation.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.presentation.Articles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items : Articles)

    @Query("SELECT * FROM articles")
    fun getAll() : List<Articles>

    @Query("DELETE FROM articles WHERE url =:url")
    fun deleteArticle(url : String)

}