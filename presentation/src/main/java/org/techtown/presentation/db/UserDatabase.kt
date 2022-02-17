package org.techtown.presentation.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        private var instance: UserDatabase ?=null

        @Synchronized
        fun getInstance(context : Context) : UserDatabase?{
            if(instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java,
                        "user-database").build()
                }
            }
            return instance
        }
    }

}