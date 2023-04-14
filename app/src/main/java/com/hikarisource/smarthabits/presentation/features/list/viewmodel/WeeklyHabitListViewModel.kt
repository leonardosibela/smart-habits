package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase

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