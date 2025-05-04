package com.example.pomodo_task.features.task.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodo_task.features.task.data.TaskEntity
import com.example.pomodo_task.features.task.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val selectedCategoryId = MutableStateFlow<Int?>(null)

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks


    init {
        viewModelScope.launch {

            selectedCategoryId.flatMapLatest { id ->
                taskRepository.filter(idCategory = id)
            }.collect { tasks ->
                _tasks.value = tasks
            }
        }
    }

    fun save(data: TaskEntity) {
        viewModelScope.launch {
            taskRepository.save(data)
        }
    }

    fun setCategoryFilter(categoryId: Int?) {
        selectedCategoryId.value = categoryId
    }
}