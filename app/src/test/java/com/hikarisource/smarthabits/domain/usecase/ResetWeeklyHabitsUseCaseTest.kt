package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_HABIT_WEEKLY
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.hikarisource.smarthabits.util.TestData.SECOND_HABIT_WEEKLY
import com.hikarisource.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ResetWeeklyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: PeriodicHabitRepository<WeeklyHabit>

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke when has habits`() = runTest {
        val habitsWeekly = listOf(FIRST_HABIT_WEEKLY, SECOND_HABIT_WEEKLY)
        coEvery { habitRepository.getAllHabitsThatAreWeekly() } returns habitsWeekly

        val lastWeeklyCounter = HABIT_COUNTER_WEEKLY
        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns lastWeeklyCounter
        val nextWeeklyCounter =
            lastWeeklyCounter.copy(id = 0, period = lastWeeklyCounter.period + 1)

        coJustRun { habitCounterRepository.insert(nextWeeklyCounter) }

        val weeklyHabits = listOf(
            FIRST_WEEKLY_HABIT.copy(completed = false, period = nextWeeklyCounter.period),
            SECOND_WEEKLY_HABIT.copy(completed = false, period = nextWeeklyCounter.period)
        )

        coEvery {
            habitToPeriodicityHabitMapper.toWeeklyHabits(
                habits = habitsWeekly,
                completed = false,
                period = nextWeeklyCounter.period
            )
        } returns weeklyHabits

        val nextWeeklyHabits = weeklyHabits.map {
            it.copy(id = 0, period = nextWeeklyCounter.period)
        }

        coJustRun { weeklyHabitRepository.save(nextWeeklyHabits[0]) }
        coJustRun { weeklyHabitRepository.save(nextWeeklyHabits[1]) }

        resetWeeklyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitCounterRepository.getLastWeeklyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextWeeklyCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreWeekly() }

        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toWeeklyHabits(
                habits = habitsWeekly,
                completed = false,
                period = nextWeeklyCounter.period
            )
        }

        coVerifyOrder {
            weeklyHabitRepository.save(nextWeeklyHabits[0])
            weeklyHabitRepository.save(nextWeeklyHabits[1])
        }

        coVerify(exactly = 1) { weeklyHabitRepository.save(nextWeeklyHabits[0]) }
        coVerify(exactly = 1) { weeklyHabitRepository.save(nextWeeklyHabits[1]) }
    }

    @Test
    fun `invoke when does not have habits`() = runTest {
        coEvery { habitRepository.getAllHabitsThatAreWeekly() } returns emptyList()

        resetWeeklyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreWeekly() }
        coVerify(exactly = 0) { habitCounterRepository.getLastWeeklyCounter() }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
        coVerify(exactly = 0) { habitToPeriodicityHabitMapper.toWeeklyHabit(any(), any(), any()) }
        coVerify(exactly = 0) { weeklyHabitRepository.save(any()) }
    }
}
