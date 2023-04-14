package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.WeeklyHabit
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
class GetCurrentWeeklyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>

    @InjectMockKs
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentHabitsUseCase<WeeklyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val expectedHabits = listOf(TestData.FIRST_WEEKLY_HABIT, TestData.SECOND_WEEKLY_HABIT)
        coEvery { weeklyHabitRepository.getHabitsForLastPeriod() } returns expectedHabits

        val result = getCurrentWeeklyHabitsUseCase.invoke()

        coVerify(exactly = 1) { weeklyHabitRepository.getHabitsForLastPeriod() }
        Assert.assertEquals(expectedHabits, result.value)
    }
}