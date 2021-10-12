package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.DailyHabitRepository
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
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
    fun invoke() = runBlocking {
        val dailyCounter = TestData.HABIT_COUNTER_DAILY
        val nextHabitCounter = dailyCounter.apply {
            id = 0
            period++
        }
        val habits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        val dailyHabits = listOf(TestData.FIRST_DAILY_HABIT, TestData.SECOND_DAILY_HABIT)
        val firstNewDailyHabit = TestData.FIRST_DAILY_HABIT
        firstNewDailyHabit.apply {
            id = 0
            period = dailyCounter.period
        }
        val secondNewDailyHabit = TestData.SECOND_DAILY_HABIT
        secondNewDailyHabit.apply {
            id = 0
            period = dailyCounter.period
        }
        coEvery { habitCounterRepository.getLastDailyCounter() } returns dailyCounter
        coJustRun { habitCounterRepository.insert(nextHabitCounter) }
        coEvery { habitRepository.getAllHabitsThatAreDaily() } returns habits
        coEvery {
            habitToPeriodicityHabitMapper.toDailyHabits(habits, false, dailyCounter.period)
        } returns dailyHabits
        coJustRun { dailyHabitRepository.save(firstNewDailyHabit) }
        coJustRun { dailyHabitRepository.save(secondNewDailyHabit) }
        resetDailyHabitsUseCase.invoke()
        coVerify(exactly = 1) { habitCounterRepository.getLastDailyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextHabitCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreDaily() }
        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toDailyHabits(habits, false, dailyCounter.period)
        }
        coVerify { dailyHabitRepository.save(firstNewDailyHabit) }
        coVerify { dailyHabitRepository.save(secondNewDailyHabit) }
    }
}