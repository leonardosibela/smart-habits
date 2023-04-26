package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.usecase.EditHabitUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHabitViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val editHabitUseCase: EditHabitUseCase,
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val DESCRIPTION_STATE_KEY = "description_state_key"
    }

    val descriptionErrorState =
        savedStateHandle.getStateFlow(DESCRIPTION_STATE_KEY, NoError)

    fun editHabit(habit: Habit, newDescription: String) = CoroutineScope(Dispatchers.IO).launch {
        if (newDescription.isBlank()) {
            savedStateHandle[DESCRIPTION_STATE_KEY] = EmptyError
        } else {
            editHabitUseCase(habit, newDescription)
        }
    }

    fun onDescriptionChanged(description: String) {
        savedStateHandle[DESCRIPTION_STATE_KEY] = if (description.isBlank()) {
            EmptyError
        } else {
            NoError
        }
    }
}
