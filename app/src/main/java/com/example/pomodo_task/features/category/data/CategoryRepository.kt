package com.example.pomodo_task.features.category.data

import com.example.pomodo_task.core.database.BaseRepository
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

    override suspend fun update(data: CategoryEntity) {
        categoryDao.update(data)
    }

}