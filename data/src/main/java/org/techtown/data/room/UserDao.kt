package org.techtown.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.data.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo WHERE is_favorite=:isFavorite")
    fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavUserInfo(userModel: UserModel): Completable?

    @Query("DELETE FROM UserInfo WHERE id=:id")
    fun deleteFavUserInfo(id: Int): Completable?
}