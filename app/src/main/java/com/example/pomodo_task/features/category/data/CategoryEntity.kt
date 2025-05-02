package com.example.pomodo_task.features.category.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_entity")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "color") val color: Int,
    @ColumnInfo(name = "active") val active: Boolean
)
