package com.example.pomodo_task.features.category.ui.screen.managerCategories

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodo_task.features.category.data.CategoryRepository
import com.example.pomodo_task.features.category.model.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class ManagerCategoryUIState(
    val categoriesAll: List<CategoryEntity> = emptyList(),
    val id: Int? = null,
    val name: String = "",
    val color: Int = 0,
    val active: Boolean = true,
    val showModal: Boolean = false,
    val isLoading: Boolean = false
)

@HiltViewModel
class ManagerCategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ManagerCategoryUIState())
    val state: StateFlow<ManagerCategoryUIState> = _state

    init {

        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            categoryRepository.getAll().collect { value ->
                _state.update {
                    it.copy(
                        categoriesAll = value,
                        isLoading = false
                    )
                }
            }
        }

    }

    private fun nameValid() = _state.value.name.trim().isNotEmpty()

    fun onIdChanged(id: Int?) {
        _state.update { it.copy(id = id) }
    }

    fun onNameChanged(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun onColorChanged(color: Int) {
        _state.update { it.copy(color = color) }
    }

    fun onActiveChanged(active: Boolean) {
        _state.update { it.copy(active = active) }
    }

    fun changeVisibility(id:Int,show:Boolean){

        viewModelScope.launch {

            categoryRepository.changeVisibility(id,show)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            categoryRepository.delete(id)
        }
    }

    fun save() {
        viewModelScope.launch {

            if (nameValid()) {

                val stateValue = _state.value

                val category = CategoryEntity(
                    color = stateValue.color,
                    active = stateValue.active,
                    name = stateValue.name
                )

                if (stateValue.id == null) {
                    categoryRepository.save(category)
                } else {
                    categoryRepository.update(category.copy(id = stateValue.id))
                }

                resetDataCategory()

            }
        }
    }

    private fun resetDataCategory() {
        _state.update {
            it.copy(
                name = "",
                color = 0,
                active = true,
                id = null
            )
        }
    }

    fun showModal(visibility: Boolean) {
        _state.update {
            it.copy(
                showModal = visibility
            )
        }
    }
}

