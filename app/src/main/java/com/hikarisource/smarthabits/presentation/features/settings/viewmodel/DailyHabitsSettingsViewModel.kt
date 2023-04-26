package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler

class DailyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
    dispatcherHandler: DispatcherHandler
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreDailyUseCase,
    deleteHabitUseCase,
    dispatcherHandler
)
