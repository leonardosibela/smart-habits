package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FinishDailyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var dailyHabitRepository: DailyHabitRepository

    @InjectMockKs
    private lateinit var finishDailyHabitUseCase: FinishDailyHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { dailyHabitRepository.remove(dailyHabit) }
        finishDailyHabitUseCase(dailyHabit)
        coVerify(exactly = 1) { dailyHabitRepository.remove(dailyHabit) }
    }
}