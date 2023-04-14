package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler

class DailyHabitListViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<DailyHabit>,
    finishDailyHabitUseCase: FinishHabitUseCase<DailyHabit>,
    dispatcherHandler: DispatcherHandler
) : HabitListViewModel<DailyHabit>(
    savedStateHandle,
    getCurrentDailyHabitsUseCase,
    finishDailyHabitUseCase,
    dispatcherHandler
) {
    override val HABITS_KEY: String
        get() = "DAILY_HABITS_KEY"

}