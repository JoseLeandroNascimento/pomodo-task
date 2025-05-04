package com.example.pomodo_task.core.database

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {

    abstract suspend fun save(data: T)

    abstract suspend fun delete(id: Int)

    abstract fun getAll(): Flow<List<T>>

    abstract suspend fun update(data: T)
}