package com.example.local.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun newsDao() : NewsDao

    companion object {
        private var INSTANCE: AppDB? = null

        //Singleton pattern
        fun getInstance(context: Context): AppDB {
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