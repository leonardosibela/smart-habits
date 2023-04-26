package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.common.resultBy
import com.hikarisource.smarthabits.domain.repository.HabitRepository

class GetHabitsThatAreMonthlyUseCase(
    private val habitRepository: HabitRepository,
) : GetHabitsFromPeriodUseCase {

    override suspend operator fun invoke() = resultBy {
        habitRepository.getAllHabitsThatAreMonthly()
    }
}
