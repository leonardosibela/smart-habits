package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.repository.*
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.HABIT_DAILY
import com.hikarisource.smarthabits.util.TestData.HABIT_MONTHLY
import com.hikarisource.smarthabits.util.TestData.HABIT_WEEKLY
import com.hikarisource.smarthabits.util.TestData.HABIT_YEARLY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class DeleteHabitUseCaseTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var dailyHabitRepository: DailyHabitRepository

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: WeeklyHabitRepository

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: MonthlyHabitRepository

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: YearlyHabitRepository

    @InjectMockKs
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun deleteDailyHabit() = runBlocking {
        val habit = HABIT_DAILY
        coJustRun { habitRepository.delete(habit) }
        coJustRun { dailyHabitRepository.removeNotCompletedByDescription(habit.description) }
        deleteHabitUseCase(habit)
        coVerify(exactly = 1) { habitRepository.delete(habit) }
        coVerify(exactly = 1) { dailyHabitRepository.removeNotCompletedByDescription(habit.description) }
    }

    @Test
    fun deleteWeeklyHabit() = runBlocking {
        val habit = HABIT_WEEKLY
        coJustRun { habitRepository.delete(habit) }
        coJustRun { weeklyHabitRepository.removeNotCompletedByDescription(habit.description) }
        deleteHabitUseCase(habit)
        coVerify(exactly = 1) { habitRepository.delete(habit) }
        coVerify(exactly = 1) { weeklyHabitRepository.removeNotCompletedByDescription(habit.description) }
    }

    @Test
    fun deleteMonthlyHabit() = runBlocking {
        val habit = HABIT_MONTHLY
        coJustRun { habitRepository.delete(habit) }
        coJustRun { monthlyHabitRepository.removeNotCompletedByDescription(habit.description) }
        deleteHabitUseCase(habit)
        coVerify(exactly = 1) { habitRepository.delete(habit) }
        coVerify(exactly = 1) { monthlyHabitRepository.removeNotCompletedByDescription(habit.description) }
    }

    @Test
    fun deleteYearlyHabit() = runBlocking {
        val habit = HABIT_YEARLY
        coJustRun { habitRepository.delete(habit) }
        coJustRun { yearlyHabitRepository.removeNotCompletedByDescription(habit.description) }
        deleteHabitUseCase(habit)
        coVerify(exactly = 1) { habitRepository.delete(habit) }
        coVerify(exactly = 1) { yearlyHabitRepository.removeNotCompletedByDescription(habit.description) }
    }
}