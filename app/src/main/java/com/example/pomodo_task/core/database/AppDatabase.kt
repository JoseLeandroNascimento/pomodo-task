package com.example.pomodo_task.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pomodo_task.features.category.data.CategoryDao
import com.example.pomodo_task.features.category.data.CategoryEntity
import com.example.pomodo_task.features.category.data.Converters

@Database(
    entities = [CategoryEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun categoryDao():CategoryDao
}