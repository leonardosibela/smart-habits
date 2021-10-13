package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.*
import com.sibela.smarthabits.domain.repository.*
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.DAILY_PERIODICITY
import com.sibela.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
import com.sibela.smarthabits.util.TestData.MONTHLY_PERIODICITY
import com.sibela.smarthabits.util.TestData.WEEKLY_PERIODICITY
import com.sibela.smarthabits.util.TestData.YEARLY_PERIODICITY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class AddHabitUseCaseTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @RelaxedMockK
    private lateinit var dailyHabitRepository: DailyHabitRepository

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: WeeklyHabitRepository

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: MonthlyHabitRepository

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: YearlyHabitRepository

    @InjectMockKs
    private lateinit var addHabitUseCase: AddHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun saveDailyHabit() = runBlocking {
        val description = FIRST_DESCRIPTION
        val periodicity = DAILY_PERIODICITY
        val habit = Habit(description = description, periodicity = periodicity)
        val lastDailyCounter = HABIT_COUNTER_DAILY
        val dailyHabit = DailyHabit(
            description = description,
            completed = false,
            period = lastDailyCounter.period
        )
        coJustRun { habitRepository.save(habit) }
        coEvery { habitCounterRepository.getLastDailyCounter() } returns lastDailyCounter
        coJustRun { dailyHabitRepository.save(dailyHabit) }
        addHabitUseCase(description, periodicity)
        coVerify(exactly = 1) { habitRepository.save(habit) }
        coVerify(exactly = 1) { habitCounterRepository.getLastDailyCounter() }
        coVerify(exactly = 1) { dailyHabitRepository.save(dailyHabit) }
    }

    @Test
    fun saveWeeklyHabit() = runBlocking {
        val description = FIRST_DESCRIPTION
        val periodicity = WEEKLY_PERIODICITY
        val habit = Habit(description = description, periodicity = periodicity)
        val lastWeeklyCounter = HABIT_COUNTER_WEEKLY
        val weeklyHabit = WeeklyHabit(
            description = description,
            completed = false,
            period = lastWeeklyCounter.period
        )
        coJustRun { habitRepository.save(habit) }
        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns lastWeeklyCounter
        coJustRun { weeklyHabitRepository.save(weeklyHabit) }
        addHabitUseCase(description, periodicity)
        coVerify(exactly = 1) { habitRepository.save(habit) }
        coVerify(exactly = 1) { habitCounterRepository.getLastWeeklyCounter() }
        coVerify(exactly = 1) { weeklyHabitRepository.save(weeklyHabit) }
    }

    @Test
    fun saveMonthlyHabit() = runBlocking {
        val description = FIRST_DESCRIPTION
        val periodicity = MONTHLY_PERIODICITY
        val habit = Habit(description = description, periodicity = periodicity)
        val lastMonthlyCounter = HABIT_COUNTER_MONTHLY
        val monthlyHabit = MonthlyHabit(
            description = description,
            completed = false,
            period = lastMonthlyCounter.period
        )
        coJustRun { habitRepository.save(habit) }
        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns lastMonthlyCounter
        coJustRun { monthlyHabitRepository.save(monthlyHabit) }
        addHabitUseCase(description, periodicity)
        coVerify(exactly = 1) { habitRepository.save(habit) }
        coVerify(exactly = 1) { habitCounterRepository.getLastMonthlyCounter() }
        coVerify(exactly = 1) { monthlyHabitRepository.save(monthlyHabit) }
    }

    @Test
    fun saveYearlyHabit() = runBlocking {
        val description = FIRST_DESCRIPTION
        val periodicity = YEARLY_PERIODICITY
        val habit = Habit(description = description, periodicity = periodicity)
        val lastYearlyCounter = HABIT_COUNTER_YEARLY
        val yearlyHabit = YearlyHabit(
            description = description,
            completed = false,
            period = lastYearlyCounter.period
        )
        coJustRun { habitRepository.save(habit) }
        coEvery { habitCounterRepository.getLastYearlyCounter() } returns lastYearlyCounter
        coJustRun { yearlyHabitRepository.save(yearlyHabit) }
        addHabitUseCase(description, periodicity)
        coVerify(exactly = 1) { habitRepository.save(habit) }
        coVerify(exactly = 1) { habitCounterRepository.getLastYearlyCounter() }
        coVerify(exactly = 1) { yearlyHabitRepository.save(yearlyHabit) }
    }
}