package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FinishMonthlyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: MonthlyHabitRepository

    @InjectMockKs
    private lateinit var finishMonthlyHabitUseCase: FinishMonthlyHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coJustRun { monthlyHabitRepository.remove(monthlyHabit) }
        finishMonthlyHabitUseCase(monthlyHabit)
        coVerify(exactly = 1) { monthlyHabitRepository.remove(monthlyHabit) }
    }
}