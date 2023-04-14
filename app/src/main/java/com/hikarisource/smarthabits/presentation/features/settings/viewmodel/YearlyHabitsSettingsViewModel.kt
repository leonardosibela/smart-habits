package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase

class YearlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreYearlyUseCase,
    deleteHabitUseCase
)