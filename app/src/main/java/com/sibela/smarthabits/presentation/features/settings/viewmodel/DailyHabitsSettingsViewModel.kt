package com.sibela.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase

class DailyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreDailyUseCase,
    deleteHabitUseCase
)