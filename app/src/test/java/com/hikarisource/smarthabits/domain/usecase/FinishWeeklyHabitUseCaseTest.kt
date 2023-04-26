package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class FinishWeeklyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>

    @InjectMockKs
    private lateinit var finishWeeklyHabitUseCase: FinishHabitUseCase<WeeklyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coJustRun { weeklyHabitRepository.remove(weeklyHabit) }

        finishWeeklyHabitUseCase(weeklyHabit)

        coVerify(exactly = 1) { weeklyHabitRepository.remove(weeklyHabit) }
    }
}
