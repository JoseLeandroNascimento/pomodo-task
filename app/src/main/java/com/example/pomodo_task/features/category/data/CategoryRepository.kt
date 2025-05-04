package com.example.pomodo_task.features.category.data

import com.example.pomodo_task.core.database.BaseRepository
import com.example.pomodo_task.features.category.model.CategoryEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
): BaseRepository<CategoryEntity> {

    override suspend fun save(data: CategoryEntity) {
        categoryDao.save(data)
    }

    override suspend fun delete(id:Int){
        categoryDao.delete(id)
    }

    override fun getAll():Flow<List<CategoryEntity>>{
        return categoryDao.getAll()
    }

    override fun getById(id: Int): Flow<CategoryEntity> {
        return categoryDao.getById(id)
    }

    override suspend fun update(data: CategoryEntity) {
        categoryDao.update(data)
    }

    fun getAllActives():Flow<List<CategoryEntity>>{
        return categoryDao.getAllActive()
    }

}