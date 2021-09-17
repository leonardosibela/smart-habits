package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.domain.repository.HabitCounterRepository
import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.domain.repository.YearlyHabitRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ResetYearlyHabitsUseCaseTest {

    @RelaxedMockK
    private lateinit var habitMapper: HabitMapper

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
    fun invoke() = runBlocking {
        val yearlyCounter = TestData.HABIT_COUNTER_YEARLY
        val nextHabitCounter = yearlyCounter.apply {
            id = 0
            period++
        }
        val habits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        val yearlyHabits = listOf(TestData.FIRST_YEARLY_HABIT, TestData.SECOND_YEARLY_HABIT)
        val firstNewYearlyHabit = TestData.FIRST_YEARLY_HABIT
        firstNewYearlyHabit.apply {
            id = 0
            period = yearlyCounter.period
        }
        val secondNewYearlyHabit = TestData.SECOND_YEARLY_HABIT
        secondNewYearlyHabit.apply {
            id = 0
            period = yearlyCounter.period
        }
        coEvery { habitCounterRepository.getLastYearlyCounter() } returns yearlyCounter
        coJustRun { habitCounterRepository.insert(nextHabitCounter) }
        coEvery { habitRepository.getAllHabitsThatAreYearly() } returns habits
        coEvery {
            habitMapper.toYearlyHabits(habits, false, yearlyCounter.period)
        } returns yearlyHabits
        coJustRun { yearlyHabitRepository.save(firstNewYearlyHabit) }
        coJustRun { yearlyHabitRepository.save(secondNewYearlyHabit) }
        resetYearlyHabitsUseCase.invoke()
        coVerify(exactly = 1) { habitCounterRepository.getLastYearlyCounter() }
        coVerify(exactly = 1) { habitCounterRepository.insert(nextHabitCounter) }
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreYearly() }
        coVerify(exactly = 1) {
            habitMapper.toYearlyHabits(habits, false, yearlyCounter.period)
        }
        coVerify { yearlyHabitRepository.save(firstNewYearlyHabit) }
        coVerify { yearlyHabitRepository.save(secondNewYearlyHabit) }
    }
}