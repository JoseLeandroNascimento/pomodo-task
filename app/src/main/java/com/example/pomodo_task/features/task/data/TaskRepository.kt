package com.example.pomodo_task.features.task.data

import com.example.pomodo_task.core.database.BaseRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TaskRepository @Inject constructor(
    val taskDao: TaskDao
):BaseRepository<TaskEntity>{

    override suspend fun save(data: TaskEntity) {
        taskDao.save(data)
    }

    override suspend fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<TaskEntity>> {
       return taskDao.getAll()
    }

    override fun getById(id: Int): Flow<TaskEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun update(data: TaskEntity) {
        TODO("Not yet implemented")
    }
}