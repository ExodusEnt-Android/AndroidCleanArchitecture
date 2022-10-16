package com.example.presentation.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insert(userModel : UserModel)

    @Query("SELECT * FROM userinfo WHERE email=:email")
    fun getChatMember(email:String) : UserModel

    @Query("SELECT * FROM userinfo")
    fun getAll(): List<UserModel>
}