package com.example.presentation.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.presentation.Items

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items : Items)

    @Query("SELECT * FROM articles")
    fun getAll() : List<Items>

    @Query("DELETE FROM articles WHERE url =:url")
    fun deleteArticle(url : String)

}