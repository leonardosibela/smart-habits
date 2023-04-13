package com.sibela.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase

class YearlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreYearlyUseCase,
    deleteHabitUseCase
)