package com.example.pomodo_task.features.category.data

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun save(data: CategoryEntity) {
        categoryDao.save(data)
    }

    suspend fun delete(id:Int){
        categoryDao.delete(id)
    }

    fun getAll():Flow<List<CategoryEntity>>{
        return categoryDao.getAll()
    }
}