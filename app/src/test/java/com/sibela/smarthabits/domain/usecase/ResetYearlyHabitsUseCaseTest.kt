package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetYearlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitToPeriodicityHabitMapper: HabitToPeriodicityHabitMapper

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: YearlyHabitRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke when has habits`() = runBlocking {
        val habitsYearly = listOf(FIRST_HABIT_YEARLY, SECOND_HABIT_YEARLY)
        coEvery { habitRepository.getAllHabitsThatAreYearly() } returns habitsYearly

        val lastYearlyCounter = HABIT_COUNTER_YEARLY
        coEvery { habitCounterRepository.getLastYearlyCounter() } returns lastYearlyCounter
        val nextYearlyCounter =
            lastYearlyCounter.copy(id = 0, period = lastYearlyCounter.period + 1)

        coJustRun { habitCounterRepository.insert(nextYearlyCounter) }

        val yearlyHabits = listOf(
            FIRST_YEARLY_HABIT.copy(completed = false, period = nextYearlyCounter.period),
            SECOND_YEARLY_HABIT.copy(completed = false, period = nextYearlyCounter.period)
        )

        coEvery {
            habitToPeriodicityHabitMapper.toYearlyHabits(
                habitsYearly, false, nextYearlyCounter.period
            )
        } returns yearlyHabits

        val nextYearlyHabits = yearlyHabits.map {
            it.copy(id = 0, period = nextYearlyCounter.period)
        }

        coJustRun { yearlyHabitRepository.save(nextYearlyHabits[0]) }
        coJustRun { yearlyHabitRepository.save(nextYearlyHabits[1]) }

        resetYearlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitCounterRepository.getLastYearlyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextYearlyCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreYearly() }

        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toYearlyHabits(
                habitsYearly, false, nextYearlyCounter.period
            )
        }

        coVerifyOrder {
            yearlyHabitRepository.save(nextYearlyHabits[0])
            yearlyHabitRepository.save(nextYearlyHabits[1])
        }

        coVerify(exactly = 1) { yearlyHabitRepository.save(nextYearlyHabits[0]) }
        coVerify(exactly = 1) { yearlyHabitRepository.save(nextYearlyHabits[1]) }
    }

    @Test
    fun `invoke when does not have habits`() = runBlocking {
        coEvery { habitRepository.getAllHabitsThatAreYearly() } returns emptyList()

        resetYearlyHabitsUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreYearly() }
        coVerify(exactly = 0) { habitCounterRepository.getLastYearlyCounter() }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
        coVerify(exactly = 0) { habitToPeriodicityHabitMapper.toYearlyHabits(any(), any(), any()) }
        coVerify(exactly = 0) { yearlyHabitRepository.save(any()) }
    }
}