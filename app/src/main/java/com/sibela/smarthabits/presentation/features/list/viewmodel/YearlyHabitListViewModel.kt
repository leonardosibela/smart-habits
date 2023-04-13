package com.sibela.smarthabits.presentation.features.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase

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