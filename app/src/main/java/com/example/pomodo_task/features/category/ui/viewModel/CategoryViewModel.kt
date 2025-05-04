package com.example.pomodo_task.features.category.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodo_task.features.category.data.CategoryEntity
import com.example.pomodo_task.features.category.data.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    private val _categoriesActive = MutableStateFlow<List<CategoryEntity>>(emptyList())

    val categories: StateFlow<List<CategoryEntity>> = _categories
    val categoriesActive: StateFlow<List<CategoryEntity>> = _categoriesActive

    init {
        viewModelScope.launch {
            categoryRepository.getAll().collect { categories ->
                _categories.value = categories
            }
        }

        viewModelScope.launch {
            categoryRepository.getAllActives().collect { categories ->
                _categoriesActive.value = categories
            }
        }
    }

    fun update(
        id: Int,
        name: String,
        color: Int,
        active: Boolean
    ) {

        viewModelScope.launch {
            categoryRepository.update(
                CategoryEntity(
                    id = id,
                    name = name,
                    color = color,
                    active = active
                )
            )
        }
    }

    fun removeCategory(id: Int) {
        viewModelScope.launch {
            categoryRepository.delete(id)
        }
    }

    fun addCategory(
        name: String,
        color: Int,
        active: Boolean
    ) {
        viewModelScope.launch {
            categoryRepository.save(
                CategoryEntity(
                    name = name,
                    color = color,
                    active = active
                )
            )
        }
    }
}