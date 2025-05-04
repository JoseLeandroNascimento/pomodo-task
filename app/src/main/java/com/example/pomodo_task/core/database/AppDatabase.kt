package com.example.pomodo_task.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pomodo_task.features.category.data.CategoryDao
import com.example.pomodo_task.features.category.model.CategoryEntity
import com.example.pomodo_task.features.category.data.Converters
import com.example.pomodo_task.features.task.data.TaskDao
import com.example.pomodo_task.features.task.data.TaskEntity

@Database(
    entities = [CategoryEntity::class,TaskEntity::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun categoryDao():CategoryDao

    abstract fun taskDao():TaskDao
}