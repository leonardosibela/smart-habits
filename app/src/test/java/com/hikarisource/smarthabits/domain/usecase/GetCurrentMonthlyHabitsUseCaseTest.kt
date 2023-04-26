package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCurrentMonthlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>

    @InjectMockKs
    private lateinit var getCurrentMonthlyHabitsUseCase: GetCurrentHabitsUseCase<MonthlyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val expectedHabits = listOf(TestData.FIRST_MONTHLY_HABIT, TestData.SECOND_MONTHLY_HABIT)
        coEvery { monthlyHabitRepository.getHabitsForLastPeriod() } returns expectedHabits

        val result = getCurrentMonthlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { monthlyHabitRepository.getHabitsForLastPeriod() }
        Assert.assertEquals(expectedHabits, result.value)
    }
}
