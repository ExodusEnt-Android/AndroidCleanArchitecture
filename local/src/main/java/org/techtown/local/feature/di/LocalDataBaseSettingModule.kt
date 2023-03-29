package org.techtown.local.feature.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.techtown.local.feature.database.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object LocalDataBaseSettingModule {

    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "articles_database"
        ).fallbackToDestructiveMigration().build()
    }
}