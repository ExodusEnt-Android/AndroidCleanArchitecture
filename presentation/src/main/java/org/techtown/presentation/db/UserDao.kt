package org.techtown.presentation.db

import androidx.room.Dao
import androidx.room.Query
import org.techtown.presentation.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo")
    fun getAllUsers(): List<UserModel>
}