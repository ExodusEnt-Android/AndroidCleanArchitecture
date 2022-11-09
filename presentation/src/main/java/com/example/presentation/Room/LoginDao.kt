package com.example.presentation.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.presentation.LoginData

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loginData : LoginData)

    @Query("SELECT * FROM login")
    fun getAll() : List<LoginData>

}