package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitToPeriodicityHabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.WeeklyHabitRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
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
    fun invoke() = runBlocking {
        val weeklyCounter = TestData.HABIT_COUNTER_WEEKLY
        val nextHabitCounter = weeklyCounter.apply {
            id = 0
            period++
        }
        val habits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        val weeklyHabits = listOf(TestData.FIRST_WEEKLY_HABIT, TestData.SECOND_WEEKLY_HABIT)
        val firstNewWeeklyHabit = TestData.FIRST_WEEKLY_HABIT
        firstNewWeeklyHabit.apply {
            id = 0
            period = weeklyCounter.period
        }
        val secondNewWeeklyHabit = TestData.SECOND_WEEKLY_HABIT
        secondNewWeeklyHabit.apply {
            id = 0
            period = weeklyCounter.period
        }
        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns weeklyCounter
        coJustRun { habitCounterRepository.insert(nextHabitCounter) }
        coEvery { habitRepository.getAllHabitsThatAreWeekly() } returns habits
        coEvery {
            habitToPeriodicityHabitMapper.toWeeklyHabits(habits, false, weeklyCounter.period)
        } returns weeklyHabits
        coJustRun { weeklyHabitRepository.save(firstNewWeeklyHabit) }
        coJustRun { weeklyHabitRepository.save(secondNewWeeklyHabit) }
        resetWeeklyHabitsUseCase.invoke()
        coVerify(exactly = 1) { habitCounterRepository.getLastWeeklyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextHabitCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreWeekly() }
        coVerify(exactly = 1) {
            habitToPeriodicityHabitMapper.toWeeklyHabits(habits, false, weeklyCounter.period)
        }
        coVerify { weeklyHabitRepository.save(firstNewWeeklyHabit) }
        coVerify { weeklyHabitRepository.save(secondNewWeeklyHabit) }
    }
}