package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase

class DailyHabitListViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<DailyHabit>,
    finishDailyHabitUseCase: FinishHabitUseCase<DailyHabit>,
) : HabitListViewModel<DailyHabit>(
    savedStateHandle,
    getCurrentDailyHabitsUseCase,
    finishDailyHabitUseCase,
) {
    override val HABITS_KEY: String
        get() = "DAILY_HABITS_KEY"

}