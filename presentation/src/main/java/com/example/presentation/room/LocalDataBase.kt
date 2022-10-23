package com.example.presentation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.presentation.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getNewsArticleDao(): NewsArticleDao
    companion object {
        fun getInstance(context: Context): LocalDataBase {
            synchronized(LocalDataBase::class.java) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    LocalDataBase::class.java, "local-database.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}