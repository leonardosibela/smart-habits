package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.common.Result
import com.hikarisource.smarthabits.domain.model.Habit

interface GetHabitsFromPeriodUseCase {

    suspend operator fun invoke(): Result<List<Habit>>
}
