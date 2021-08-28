package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.resultBy
import com.sibela.smarthabits.domain.repository.HabitRepository

class GetHabitsThatAreDailyUseCase(private val habitRepository: HabitRepository) {

    internal suspend operator fun invoke() = resultBy {
        habitRepository.getAllHabitsThatAreDaily()
    }
}