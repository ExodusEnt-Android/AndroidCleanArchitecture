package com.example.presentation.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.presentation.const.Const
import com.example.presentation.model.Article
import com.example.presentation.util.PreferenceManager

@Database(
    entities = [Article::class],
    version = 5,
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
                        LocalDataBase::class.java,
                        "${
                            PreferenceManager.getPreference(
                                context,
                                Const.PARAM_IS_LOGIN_ID,
                                ""
                            )
                        }_local-database.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance(){
            instance = null
        }
    }

}