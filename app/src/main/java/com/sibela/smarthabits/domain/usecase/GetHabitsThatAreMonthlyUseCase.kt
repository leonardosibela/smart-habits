package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.HabitRepository

class GetHabitsThatAreMonthlyUseCase(private val habitRepository: HabitRepository) {

    suspend operator fun invoke() = resultBy {
        habitRepository.getAllHabitsThatAreMonthly()
    }
}