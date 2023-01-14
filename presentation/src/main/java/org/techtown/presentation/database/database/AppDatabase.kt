package org.techtown.presentation.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.techtown.presentation.database.ArticlesDao
import org.techtown.presentation.model.Articles

@Database(
    entities = [Articles::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "articles_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}