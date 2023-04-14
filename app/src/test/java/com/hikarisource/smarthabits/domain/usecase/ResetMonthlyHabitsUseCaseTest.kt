package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_HABIT_MONTHLY
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.hikarisource.smarthabits.util.TestData.SECOND_HABIT_MONTHLY
import com.hikarisource.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetMonthlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var monthlyHabitRepository: MonthlyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke when has habits`() = runBlocking {
        val habitsMonthly = listOf(FIRST_HABIT_MONTHLY, SECOND_HABIT_MONTHLY)
        coEvery { habitRepository.getAllHabitsThatAreMonthly() } returns habitsMonthly

        val lastMonthlyCounter = HABIT_COUNTER_MONTHLY
        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns lastMonthlyCounter
        val nextMonthlyCounter =
            lastMonthlyCounter.copy(id = 0, period = lastMonthlyCounter.period + 1)

        coJustRun { habitCounterRepository.insert(nextMonthlyCounter) }

        val monthlyHabits = listOf(
            FIRST_MONTHLY_HABIT.copy(completed = false, period = nextMonthlyCounter.period),
            SECOND_MONTHLY_HABIT.copy(completed = false, period = nextMonthlyCounter.period)
        )

        coEvery {
            habitToPeriodicityHabitMapper.toMonthlyHabits(
                habitsMonthly, false, nextMonthlyCounter.period
            )
        } returns monthlyHabits

        val nextMonthlyHabits = monthlyHabits.map {
            it.copy(id = 0, period = nextMonthlyCounter.period)
        }

        coJustRun { monthlyHabitRepository.save(nextMonthlyHabits[0]) }
        coJustRun { monthlyHabitRepository.save(nextMonthlyHabits[1]) }

        resetMonthlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitCounterRepository.getLastMonthlyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextMonthlyCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreMonthly() }

        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toMonthlyHabits(
                habitsMonthly, false, nextMonthlyCounter.period
            )
        }

        coVerifyOrder {
            monthlyHabitRepository.save(nextMonthlyHabits[0])
            monthlyHabitRepository.save(nextMonthlyHabits[1])
        }

        coVerify(exactly = 1) { monthlyHabitRepository.save(nextMonthlyHabits[0]) }
        coVerify(exactly = 1) { monthlyHabitRepository.save(nextMonthlyHabits[1]) }
    }

    @Test
    fun `invoke when does not have habits`() = runBlocking {
        coEvery { habitRepository.getAllHabitsThatAreMonthly() } returns emptyList()

        resetMonthlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreMonthly() }
        coVerify(exactly = 0) { habitCounterRepository.getLastMonthlyCounter() }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
        coVerify(exactly = 0) { habitToPeriodicityHabitMapper.toMonthlyHabits(any(), any(), any()) }
        coVerify(exactly = 0) { monthlyHabitRepository.save(any()) }
    }
}