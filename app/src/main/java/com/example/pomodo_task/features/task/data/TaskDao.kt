package com.example.pomodo_task.features.task.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun save(data: TaskEntity)

    @Query(value = "SELECT * FROM task_entity")
    fun getAll():Flow<List<TaskEntity>>

}