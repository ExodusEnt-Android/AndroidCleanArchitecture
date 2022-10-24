package com.example.presentation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.presentation.model.Article

@Database(
    entities = [Article::class],
    version = 4,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getNewsArticleDao(): NewsArticleDao

    companion object {
        private var instance: LocalDataBase? = null

        @Synchronized
        fun getInstance(context: Context): LocalDataBase? {

            if (instance == null) {
                synchronized(LocalDataBase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDataBase::class.java, "local-database.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}