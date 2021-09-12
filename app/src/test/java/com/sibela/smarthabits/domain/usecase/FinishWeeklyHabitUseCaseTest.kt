package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FinishWeeklyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: WeeklyHabitRepository

    @InjectMockKs
    private lateinit var finishWeeklyHabitUseCase: FinishWeeklyHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coJustRun { weeklyHabitRepository.remove(weeklyHabit) }
        finishWeeklyHabitUseCase(weeklyHabit)
        coVerify(exactly = 1) { weeklyHabitRepository.remove(weeklyHabit) }
    }
}