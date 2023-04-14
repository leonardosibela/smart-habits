package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetCurrentYearlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: YearlyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val expectedHabits = listOf(TestData.FIRST_YEARLY_HABIT, TestData.SECOND_YEARLY_HABIT)
        val habitCounter = TestData.HABIT_COUNTER_YEARLY
        coEvery { habitCounterRepository.getLastYearlyCounter() } returns habitCounter
        coEvery { yearlyHabitRepository.getHabitsForPeriod(habitCounter.period) } returns expectedHabits
        val result = getCurrentYearlyHabitsUseCase.invoke()
        coVerify(exactly = 1) { yearlyHabitRepository.getHabitsForPeriod(habitCounter.period) }
        Assert.assertEquals(expectedHabits, result.value)
    }
}