package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetCurrentWeeklyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: WeeklyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentWeeklyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val expectedHabits = listOf(TestData.FIRST_WEEKLY_HABIT, TestData.SECOND_WEEKLY_HABIT)
        val habitCounter = TestData.HABIT_COUNTER_WEEKLY
        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns habitCounter
        coEvery { weeklyHabitRepository.getHabitsForPeriod(habitCounter.period) } returns expectedHabits
        val result = getCurrentWeeklyHabitsUseCase.invoke()
        coVerify(exactly = 1) { weeklyHabitRepository.getHabitsForPeriod(habitCounter.period) }
        Assert.assertEquals(expectedHabits, result.value)
    }
}