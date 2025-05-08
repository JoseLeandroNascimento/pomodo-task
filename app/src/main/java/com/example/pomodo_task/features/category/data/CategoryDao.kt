package com.example.pomodo_task.features.category.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pomodo_task.features.category.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun save(dado: CategoryEntity)

    @Query(value = "SELECT * FROM category_entity")
    fun getAll(): Flow<List<CategoryEntity>>

    @Query("DELETE FROM category_entity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query(value = "SELECT * FROM category_entity WHERE active = 1")
    fun getAllActive(): Flow<List<CategoryEntity>>

    @Query(value = "SELECT * FROM category_entity WHERE id = :id")
    fun getById(id:Int):Flow<CategoryEntity>

    @Query("UPDATE category_entity SET active = :show WHERE id = :id")
    suspend fun changeVisibility(id: Int, show: Boolean)


    @Update
    suspend fun update(data: CategoryEntity)
}