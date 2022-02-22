package org.techtown.presentation.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Flowable
import org.techtown.presentation.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo WHERE is_favorite=:isFavorite")
    fun getFavUserInfo(isFavorite: Boolean): Flowable<UserModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavUserInfo(userModel: UserModel)
}