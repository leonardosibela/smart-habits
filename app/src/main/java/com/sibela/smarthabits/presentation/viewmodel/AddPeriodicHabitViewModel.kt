package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.usecase.AddHabitUseCase
import kotlinx.coroutines.launch

class AddPeriodicHabitViewModel(private val addHabitUseCase: AddHabitUseCase) : ViewModel() {

    fun addHabit(description: String, periodicity: Periodicity) = viewModelScope.launch {
        addHabitUseCase(description, periodicity)
    }
}