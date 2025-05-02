package com.example.pomodo_task.features.category.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun save(dado: CategoryEntity)

    @Query(value = "SELECT * FROM category_entity")
    fun getAll(): Flow<List<CategoryEntity>>

    @Query("DELETE FROM category_entity WHERE id = :id")
    suspend fun delete(id: Int)
}