package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase

class YearlyHabitListViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentHabitsUseCase: GetCurrentHabitsUseCase<YearlyHabit>,
    finishHabitUseCase: FinishHabitUseCase<YearlyHabit>,
) : HabitListViewModel<YearlyHabit>(
    savedStateHandle,
    getCurrentHabitsUseCase,
    finishHabitUseCase
) {

    override val HABITS_KEY: String
        get() = "YEARLY_HABITS_KEY"
}