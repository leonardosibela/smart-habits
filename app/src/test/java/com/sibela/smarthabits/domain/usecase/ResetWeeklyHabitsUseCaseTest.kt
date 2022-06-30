package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetWeeklyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var weeklyHabitRepository: WeeklyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke when has habits`() = runBlocking {
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
                habitsWeekly, false, nextWeeklyCounter.period
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
                habitsWeekly, false, nextWeeklyCounter.period
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
    fun `invoke when does not have habits`() = runBlocking {
        coEvery { habitRepository.getAllHabitsThatAreWeekly() } returns emptyList()

        resetWeeklyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreWeekly() }
        coVerify(exactly = 0) { habitCounterRepository.getLastWeeklyCounter() }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
        coVerify(exactly = 0) { habitToPeriodicityHabitMapper.toWeeklyHabit(any(), any(), any()) }
        coVerify(exactly = 0) { weeklyHabitRepository.save(any()) }
    }
}