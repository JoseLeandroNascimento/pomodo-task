package com.example.pomodo_task.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateCategoryModalViewModel:ViewModel() {

    private val _showModal = MutableStateFlow(false)
    val showModal:StateFlow<Boolean> = _showModal

    fun changeVisibility(){
        _showModal.value = !_showModal.value
    }


}