package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
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
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EditHabitUseCaseTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var dailyHabitRepository: PeriodicHabitRepository<DailyHabit>

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: PeriodicHabitRepository<MonthlyHabit>

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>

    @InjectMockKs
    private lateinit var editHabitUseCase: EditHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun editDailyHabit() = runTest {
        val habit = HABIT_DAILY
        val newDescription = FIRST_DESCRIPTION
        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
        coJustRun { dailyHabitRepository.removeNotCompletedByDescription(habit.description) }

        editHabitUseCase(habit, newDescription)

        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
        coVerify(exactly = 1) {
            dailyHabitRepository.updateNotCompletedDescription(habit.description, newDescription)
        }
        coVerify(exactly = 0) {
            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
    }

    @Test
    fun editWeeklyHabit() = runTest {
        val habit = HABIT_WEEKLY
        val newDescription = FIRST_DESCRIPTION
        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
        coJustRun { weeklyHabitRepository.removeNotCompletedByDescription(habit.description) }

        editHabitUseCase(habit, newDescription)

        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
        coVerify(exactly = 1) {
            weeklyHabitRepository.updateNotCompletedDescription(habit.description, newDescription)
        }
        coVerify(exactly = 0) {
            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
    }

    @Test
    fun editMonthlyHabit() = runTest {
        val habit = HABIT_MONTHLY
        val newDescription = FIRST_DESCRIPTION
        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
        coJustRun { monthlyHabitRepository.removeNotCompletedByDescription(habit.description) }

        editHabitUseCase(habit, newDescription)

        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
        coVerify(exactly = 1) {
            monthlyHabitRepository.updateNotCompletedDescription(habit.description, newDescription)
        }
        coVerify(exactly = 0) {
            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            yearlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
    }

    @Test
    fun editYearlyHabit() = runTest {
        val habit = HABIT_YEARLY
        val newDescription = FIRST_DESCRIPTION
        coJustRun { habitRepository.editHabitDescription(habit.id, newDescription) }
        coJustRun { yearlyHabitRepository.removeNotCompletedByDescription(habit.description) }

        editHabitUseCase(habit, newDescription)

        coVerify(exactly = 1) { habitRepository.editHabitDescription(habit.id, newDescription) }
        coVerify(exactly = 1) {
            yearlyHabitRepository.updateNotCompletedDescription(habit.description, newDescription)
        }
        coVerify(exactly = 0) {
            dailyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            weeklyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
        coVerify(exactly = 0) {
            monthlyHabitRepository.updateNotCompletedDescription(any<String>(), any())
        }
    }
}