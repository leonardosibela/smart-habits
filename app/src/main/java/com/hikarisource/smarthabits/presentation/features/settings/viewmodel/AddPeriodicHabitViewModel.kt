package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.usecase.AddHabitUseCase
import kotlinx.coroutines.launch

class AddPeriodicHabitViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addHabitUseCase: AddHabitUseCase,
) : ViewModel() {

    companion object {
        private const val DESCRIPTION_STATE_KEY = "description_state_key"
    }

    val descriptionErrorState = savedStateHandle.getStateFlow(DESCRIPTION_STATE_KEY, NoError)

    fun addHabit(description: String, periodicity: Periodicity) = viewModelScope.launch {
        if (description.isBlank()) {
            savedStateHandle[DESCRIPTION_STATE_KEY] = EmptyError
        } else {
            addHabitUseCase(description, periodicity)
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