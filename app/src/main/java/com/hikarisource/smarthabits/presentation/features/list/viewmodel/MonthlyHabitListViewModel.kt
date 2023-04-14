package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase

class MonthlyHabitListViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentHabitsUseCase: GetCurrentHabitsUseCase<MonthlyHabit>,
    finishHabitUseCase: FinishHabitUseCase<MonthlyHabit>,
) : HabitListViewModel<MonthlyHabit>(
    savedStateHandle,
    getCurrentHabitsUseCase,
    finishHabitUseCase
) {

    override val HABITS_KEY: String
        get() = "MONTHLY_HABITS_KEY"
}