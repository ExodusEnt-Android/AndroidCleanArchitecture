package com.example.presentation.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.presentation.Articles
import com.example.presentation.LoginData

@Database(entities = [Articles::class, LoginData :: class], version = 5, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun newsDao() : NewsDao
    abstract fun loginDao() : LoginDao

    companion object {
        private var INSTANCE: AppDB? = null

        //Singleton pattern
        fun getInstance(context: Context): AppDB? {
            if (INSTANCE == null) {
                //synchronized : 중복 방지
                synchronized(AppDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDB::class.java, "recipe.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}