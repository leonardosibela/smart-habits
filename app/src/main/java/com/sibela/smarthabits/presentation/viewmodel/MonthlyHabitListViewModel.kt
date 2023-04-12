package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase

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