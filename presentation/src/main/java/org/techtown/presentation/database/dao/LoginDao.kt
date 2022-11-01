package org.techtown.presentation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.techtown.presentation.model.LoginModel

@Dao
interface LoginDao {

    @Insert
    fun insertLoginStatus(loginModel: LoginModel)

    @Query("SELECT * FROM login")
    fun getIsLogined() : List<LoginModel>
}