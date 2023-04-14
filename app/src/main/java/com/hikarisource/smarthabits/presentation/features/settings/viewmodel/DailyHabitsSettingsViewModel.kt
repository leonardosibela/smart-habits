package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase

class DailyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreDailyUseCase,
    deleteHabitUseCase
)