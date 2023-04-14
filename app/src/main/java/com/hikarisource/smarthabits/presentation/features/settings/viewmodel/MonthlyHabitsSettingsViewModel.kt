package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler

class MonthlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreMonthlyUseCase: GetHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
    dispatcherHandler: DispatcherHandler
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreMonthlyUseCase,
    deleteHabitUseCase,
    dispatcherHandler
)