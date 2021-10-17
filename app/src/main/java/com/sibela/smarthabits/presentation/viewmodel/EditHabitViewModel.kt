package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.EditHabitUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHabitViewModel(private val editHabitUseCase: EditHabitUseCase) : ViewModel() {

    fun editHabit(habit: Habit, newDescription: String) = CoroutineScope(Dispatchers.IO).launch {
        editHabitUseCase(habit, newDescription)
    }
}