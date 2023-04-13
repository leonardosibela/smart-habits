package com.sibela.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase

class MonthlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreMonthlyUseCase: GetHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase
)