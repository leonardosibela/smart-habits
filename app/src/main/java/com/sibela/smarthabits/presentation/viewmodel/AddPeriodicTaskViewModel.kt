package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.usecase.AddTaskUseCase
import kotlinx.coroutines.launch

class AddPeriodicTaskViewModel(private val addTaskUseCase: AddTaskUseCase) : ViewModel() {

    fun addTask(description: String, periodicity: Periodicity) = viewModelScope.launch {
        addTaskUseCase(description, periodicity)
    }
}