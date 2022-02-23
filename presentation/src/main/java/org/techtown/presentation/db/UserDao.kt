package org.techtown.presentation.db

import androidx.lifecycle.LiveData
import androidx.room.*
import org.techtown.presentation.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo WHERE is_favorite=:isFavorite")
    fun getFavUserInfo(isFavorite: Boolean): LiveData<List<UserModel>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavUserInfo(userModel: UserModel)

    @Query("DELETE FROM UserInfo WHERE id=:id")
    fun deleteFavUserInfo(id: Int)
}