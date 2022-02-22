package org.techtown.presentation.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.techtown.presentation.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo WHERE is_favorite=:isFavorite")
    fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavUserInfo(userModel: UserModel)
}