package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler

class WeeklyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
    dispatcherHandler: DispatcherHandler
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreWeeklyUseCase,
    deleteHabitUseCase,
    dispatcherHandler
)