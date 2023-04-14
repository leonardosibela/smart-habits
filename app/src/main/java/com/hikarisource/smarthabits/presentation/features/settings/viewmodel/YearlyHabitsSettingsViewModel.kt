package com.hikarisource.smarthabits.presentation.features.settings.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler

class YearlyHabitsSettingsViewModel(
    savedStateHandle: SavedStateHandle,
    getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase,
    deleteHabitUseCase: DeleteHabitUseCase,
    dispatcherHandler: DispatcherHandler,
) : HabitsSettingsViewModel(
    savedStateHandle,
    getHabitsThatAreYearlyUseCase,
    deleteHabitUseCase,
    dispatcherHandler
)