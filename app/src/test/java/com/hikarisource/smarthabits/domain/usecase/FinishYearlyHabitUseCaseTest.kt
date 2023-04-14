package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FinishYearlyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: YearlyHabitRepository

    @InjectMockKs
    private lateinit var finishYearlyHabitUseCase: FinishYearlyHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coJustRun { yearlyHabitRepository.remove(yearlyHabit) }
        finishYearlyHabitUseCase(yearlyHabit)
        coVerify(exactly = 1) { yearlyHabitRepository.remove(yearlyHabit) }
    }
}