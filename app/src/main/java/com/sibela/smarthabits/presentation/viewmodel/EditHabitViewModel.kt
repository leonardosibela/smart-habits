package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.EditHabitUseCase
import kotlinx.coroutines.launch

class EditHabitViewModel(private val editHabitUseCase: EditHabitUseCase) : ViewModel() {

    fun editHabit(habit: Habit, newDescription: String) = viewModelScope.launch {
        editHabitUseCase(habit, newDescription)
    }
}