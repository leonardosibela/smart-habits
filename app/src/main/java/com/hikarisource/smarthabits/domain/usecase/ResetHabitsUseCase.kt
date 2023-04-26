package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.common.extension.isFirstDayOfMonth
import com.hikarisource.smarthabits.common.extension.isFirstDayOfWeek
import com.hikarisource.smarthabits.common.extension.isFirstDayOfYear
import java.time.LocalDateTime

class ResetHabitsUseCase(
    private val resetDailyHabitsUseCase: ResetDailyHabitsUseCase,
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase,
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase,
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase,
) {

    suspend operator fun invoke() {
        resetDailyHabitsUseCase.invoke()
        val dateTime = LocalDateTime.now()
        if (dateTime.isFirstDayOfWeek()) {
            resetWeeklyHabitsUseCase.invoke()
        }
        if (dateTime.isFirstDayOfMonth()) {
            resetMonthlyHabitsUseCase.invoke()
        }
        if (dateTime.isFirstDayOfYear()) {
            resetYearlyHabitsUseCase.invoke()
        }
    }
}
