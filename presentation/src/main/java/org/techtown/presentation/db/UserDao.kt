package org.techtown.presentation.db

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.techtown.presentation.model.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo WHERE is_favorite=:isFavorite")
    fun getFavUserInfo(isFavorite: Boolean): Single<List<UserModel>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavUserInfo(userModel: UserModel): Completable?

    @Query("DELETE FROM UserInfo WHERE id=:id")
    fun deleteFavUserInfo(id: Int): Completable?
}