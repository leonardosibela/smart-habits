package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase

class WeeklyHabitListViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentHabitsUseCase: GetCurrentHabitsUseCase<WeeklyHabit>,
    finishHabitUseCase: FinishHabitUseCase<WeeklyHabit>,
) : HabitListViewModel<WeeklyHabit>(
    savedStateHandle,
    getCurrentHabitsUseCase,
    finishHabitUseCase,
) {

    override val HABITS_KEY: String
        get() = "WEEKLY_HABITS_KEY"
}