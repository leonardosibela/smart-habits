package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.model.Task
import com.sibela.smarthabits.domain.usecase.EditTaskUseCase
import kotlinx.coroutines.launch

class EditTaskViewModel(private val editTaskUseCase: EditTaskUseCase) : ViewModel() {

    fun editTask(task: Task) = viewModelScope.launch {
        editTaskUseCase(task)
    }
}