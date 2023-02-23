package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.extension.isFirstDayOfMonth
import com.sibela.smarthabits.extension.isFirstDayOfWeek
import com.sibela.smarthabits.extension.isFirstDayOfYear
import java.util.Calendar

class ResetHabitsUseCase(
    private val resetDailyHabitsUseCase: ResetDailyHabitsUseCase,
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase,
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase,
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase
) {

    suspend operator fun invoke() {
        resetDailyHabitsUseCase.invoke()
        val currentCalendar = Calendar.getInstance()
        if (currentCalendar.isFirstDayOfWeek()) {
            resetWeeklyHabitsUseCase.invoke()
        }
        if (currentCalendar.isFirstDayOfMonth()) {
            resetMonthlyHabitsUseCase.invoke()
        }
        if (currentCalendar.isFirstDayOfYear()) {
            resetYearlyHabitsUseCase.invoke()
        }
    }
}