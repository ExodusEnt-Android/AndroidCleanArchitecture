package com.example.presentation.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsModel::class], version = 4, exportSchema = false)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDao() : NewsDao

    companion object {
        private var INSTANCE: NewsDB? = null

        //Singleton pattern
        fun getInstance(context: Context): NewsDB? {
            if (INSTANCE == null) {
                //synchronized : 중복 방지
                synchronized(NewsDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NewsDB::class.java, "recipe.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}