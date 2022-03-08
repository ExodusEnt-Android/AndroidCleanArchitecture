package org.techtown.presentation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techtown.presentation.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): UserDatabase? {
            if (instance == null && context != null) {
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "room-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }

}