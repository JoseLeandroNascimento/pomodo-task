package com.example.pomodo_task.core.di

import android.content.Context
import androidx.room.Room
import com.example.pomodo_task.core.database.AppDatabase
import com.example.pomodo_task.features.category.data.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }
}