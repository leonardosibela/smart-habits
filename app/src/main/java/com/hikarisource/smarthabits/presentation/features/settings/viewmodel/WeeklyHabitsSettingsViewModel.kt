package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase

class WeeklyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase,
)