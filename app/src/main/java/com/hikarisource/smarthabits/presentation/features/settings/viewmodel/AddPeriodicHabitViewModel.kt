package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.usecase.AddHabitUseCase
import kotlinx.coroutines.launch

class AddPeriodicHabitViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addHabitUseCase: AddHabitUseCase
) : ViewModel() {

    companion object {
        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        const val DESCRIPTION_STATE_KEY = "description_state_key"
    }

    val descriptionErrorState = savedStateHandle.getStateFlow(DESCRIPTION_STATE_KEY, Disable)

    fun addHabit(description: String, periodicity: Periodicity) = viewModelScope.launch {
        addHabitUseCase(
            description = description,
            periodicity = periodicity
        )
    }

    fun onDescriptionChanged(description: String) {
        savedStateHandle[DESCRIPTION_STATE_KEY] = if (description.isBlank()) {
            Disable
        } else {
            Enable
        }
    }
}
