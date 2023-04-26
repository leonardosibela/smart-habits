package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.DailyHabit
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
class GetCurrentDailyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var dailyHabitRepository: PeriodicHabitRepository<DailyHabit>

    @InjectMockKs
    private lateinit var getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<DailyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val expectedHabits = listOf(TestData.FIRST_DAILY_HABIT, TestData.SECOND_DAILY_HABIT)
        coEvery { dailyHabitRepository.getHabitsForLastPeriod() } returns expectedHabits

        val result = getCurrentDailyHabitsUseCase.invoke()

        coVerify(exactly = 1) { dailyHabitRepository.getHabitsForLastPeriod() }
        Assert.assertEquals(expectedHabits, result.value)
    }
}
