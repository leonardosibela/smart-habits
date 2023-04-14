package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.YearlyHabit
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
class GetCurrentYearlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>

    @InjectMockKs
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentHabitsUseCase<YearlyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val expectedHabits = listOf(TestData.FIRST_YEARLY_HABIT, TestData.SECOND_YEARLY_HABIT)
        coEvery { yearlyHabitRepository.getHabitsForLastPeriod() } returns expectedHabits

        val result = getCurrentYearlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { yearlyHabitRepository.getHabitsForLastPeriod() }
        Assert.assertEquals(expectedHabits, result.value)
    }
}