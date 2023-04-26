package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.DAILY_PERIODICITY
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
import com.hikarisource.smarthabits.util.TestData.MONTHLY_PERIODICITY
import com.hikarisource.smarthabits.util.TestData.WEEKLY_PERIODICITY
import com.hikarisource.smarthabits.util.TestData.YEARLY_PERIODICITY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class AddHabitUseCaseTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @RelaxedMockK
    private lateinit var dailyHabitRepository: PeriodicHabitRepository<DailyHabit>

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>

    @InjectMockKs
    private lateinit var addHabitUseCase: AddHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun saveDailyHabit() = runTest {
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
    fun saveWeeklyHabit() = runTest {
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
    fun saveMonthlyHabit() = runTest {
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
    fun saveYearlyHabit() = runTest {
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
