package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.DailyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetDailyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var dailyHabitRepository: DailyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var resetDailyHabitsUseCase: ResetDailyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke when has habits`() = runBlocking {
        val habitsDaily = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        coEvery { habitRepository.getAllHabitsThatAreDaily() } returns habitsDaily

        val lastDailyCounter = HABIT_COUNTER_DAILY
        coEvery { habitCounterRepository.getLastDailyCounter() } returns lastDailyCounter
        val nextDailyCounter =
            lastDailyCounter.copy(id = 0, period = lastDailyCounter.period + 1)

        coJustRun { habitCounterRepository.insert(nextDailyCounter) }

        val dailyHabits = listOf(
            FIRST_DAILY_HABIT.copy(completed = false, period = nextDailyCounter.period),
            SECOND_DAILY_HABIT.copy(completed = false, period = nextDailyCounter.period)
        )

        coEvery {
            habitToPeriodicityHabitMapper.toDailyHabits(
                habitsDaily, false, nextDailyCounter.period
            )
        } returns dailyHabits

        val nextDailyHabits = dailyHabits.map {
            it.copy(id = 0, period = nextDailyCounter.period)
        }

        coJustRun { dailyHabitRepository.save(nextDailyHabits[0]) }
        coJustRun { dailyHabitRepository.save(nextDailyHabits[1]) }

        resetDailyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitCounterRepository.getLastDailyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextDailyCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreDaily() }

        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toDailyHabits(
                habitsDaily, false, nextDailyCounter.period
            )
        }

        coVerifyOrder {
            dailyHabitRepository.save(nextDailyHabits[0])
            dailyHabitRepository.save(nextDailyHabits[1])
        }

        coVerify(exactly = 1) { dailyHabitRepository.save(nextDailyHabits[0]) }
        coVerify(exactly = 1) { dailyHabitRepository.save(nextDailyHabits[1]) }
    }

    @Test
    fun `invoke when does not have habits`() = runBlocking {
        coEvery { habitRepository.getAllHabitsThatAreDaily() } returns emptyList()

        resetDailyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreDaily() }
        coVerify(exactly = 0) { habitCounterRepository.getLastDailyCounter() }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
        coVerify(exactly = 0) { habitToPeriodicityHabitMapper.toDailyHabits(any(), any(), any()) }
        coVerify(exactly = 0) { dailyHabitRepository.save(any()) }
    }
}