package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.MonthlyHabitRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
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
    fun invoke() = runBlocking {
        val monthlyCounter = TestData.HABIT_COUNTER_MONTHLY
        val nextHabitCounter = monthlyCounter.apply {
            id = 0
            period++
        }
        val habits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        val monthlyHabits = listOf(TestData.FIRST_MONTHLY_HABIT, TestData.SECOND_MONTHLY_HABIT)
        val firstNewMonthlyHabit = TestData.FIRST_MONTHLY_HABIT
        firstNewMonthlyHabit.apply {
            id = 0
            period = monthlyCounter.period
        }
        val secondNewMonthlyHabit = TestData.SECOND_MONTHLY_HABIT
        secondNewMonthlyHabit.apply {
            id = 0
            period = monthlyCounter.period
        }
        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns monthlyCounter
        coJustRun { habitCounterRepository.insert(nextHabitCounter) }
        coEvery { habitRepository.getAllHabitsThatAreMonthly() } returns habits
        coEvery {
            habitToPeriodicityHabitMapper.toMonthlyHabits(habits, false, monthlyCounter.period)
        } returns monthlyHabits
        coJustRun { monthlyHabitRepository.save(firstNewMonthlyHabit) }
        coJustRun { monthlyHabitRepository.save(secondNewMonthlyHabit) }
        resetMonthlyHabitsUseCase.invoke()
        coVerify(exactly = 1) { habitCounterRepository.getLastMonthlyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextHabitCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreMonthly() }
        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toMonthlyHabits(habits, false, monthlyCounter.period)
        }
        coVerify { monthlyHabitRepository.save(firstNewMonthlyHabit) }
        coVerify { monthlyHabitRepository.save(secondNewMonthlyHabit) }
    }
}