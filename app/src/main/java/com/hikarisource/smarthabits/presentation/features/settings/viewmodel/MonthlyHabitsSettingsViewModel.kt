package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase

class MonthlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreMonthlyUseCase: GetHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase
)