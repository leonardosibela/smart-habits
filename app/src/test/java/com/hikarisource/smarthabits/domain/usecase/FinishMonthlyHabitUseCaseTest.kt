package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class FinishMonthlyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>

    @InjectMockKs
    private lateinit var finishMonthlyHabitUseCase: FinishHabitUseCase<MonthlyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coJustRun { monthlyHabitRepository.remove(monthlyHabit) }
        finishMonthlyHabitUseCase(monthlyHabit)
        coVerify(exactly = 1) { monthlyHabitRepository.remove(monthlyHabit) }
    }
}