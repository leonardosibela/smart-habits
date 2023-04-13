package com.sibela.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase

class WeeklyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase,
)