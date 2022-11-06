package com.example.presentation.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM newsModel")
    fun getAll(): List<NewsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(newsModel : NewsModel)

    @Query("DELETE FROM newsModel WHERE id = :id")
    fun deleteData(id: Long)

}