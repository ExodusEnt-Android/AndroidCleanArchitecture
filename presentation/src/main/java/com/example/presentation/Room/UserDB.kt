package com.example.presentation.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{

        @Volatile private var instance: UserDB? = null

        @Synchronized
        fun getInstance(context: Context) : UserDB? {
            if(instance == null){
                synchronized(UserDB::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        "user-db"
                    ).build()
                }
            }
            return instance
        }
    }
}