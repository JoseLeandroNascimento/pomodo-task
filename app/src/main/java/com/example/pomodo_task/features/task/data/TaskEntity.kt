package com.example.pomodo_task.features.task.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pomodo_task.features.category.model.CategoryEntity
import java.util.Date

@Entity(
    tableName = "task_entity",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            childColumns = arrayOf("category_id"),
            parentColumns = arrayOf("id"),
            onUpdate = ForeignKey.SET_NULL,
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "priority") val priority:Int,
    @ColumnInfo(name = "description", typeAffinity = ColumnInfo.TEXT) val description: String,
    @ColumnInfo(name = "date_limit") val dateLimit:Date
)
