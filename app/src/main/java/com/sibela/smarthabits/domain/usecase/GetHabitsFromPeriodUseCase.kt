package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.common.Result
import com.sibela.smarthabits.domain.model.Habit

interface GetHabitsFromPeriodUseCase {

    suspend operator fun invoke(): Result<List<Habit>>

}