package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class FinishDailyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var dailyHabitRepository: PeriodicHabitRepository<DailyHabit>

    @InjectMockKs
    private lateinit var finishDailyHabitUseCase: FinishHabitUseCase<DailyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { dailyHabitRepository.remove(dailyHabit) }

        finishDailyHabitUseCase(dailyHabit)

        coVerify(exactly = 1) { dailyHabitRepository.remove(dailyHabit) }
    }
}
