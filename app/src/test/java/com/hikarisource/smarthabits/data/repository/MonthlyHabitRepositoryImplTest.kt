package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.MonthlyHabitDao
import com.hikarisource.smarthabits.data.mapper.MonthlyHabitMapper
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_ID
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.hikarisource.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_MONTHLY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MonthlyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var monthlyHabitDao: MonthlyHabitDao

    @RelaxedMockK
    private lateinit var monthlyHabitMapper: MonthlyHabitMapper

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var monthlyHabitRepositoryImpl: MonthlyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runTest {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        val monthlyHabitEntity = FIRST_MONTHLY_HABIT_ENTITY
        coJustRun { monthlyHabitDao.insert(monthlyHabitEntity) }
        every { monthlyHabitMapper.fromDomain(monthlyHabit) } returns monthlyHabitEntity

        monthlyHabitRepositoryImpl.save(monthlyHabit)

        verify(exactly = 1) { monthlyHabitMapper.fromDomain(monthlyHabit) }
        coVerify(exactly = 1) { monthlyHabitDao.insert(monthlyHabitEntity) }
    }

    @Test
    fun getHabitsForPeriod() = runTest {
        val habitCounter = HABIT_COUNTER_MONTHLY
        val expectedHabits = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        val expectedHabitEntities = listOf(FIRST_MONTHLY_HABIT_ENTITY, SECOND_MONTHLY_HABIT_ENTITY)

        coEvery { habitCounterRepository.getLastMonthlyCounter() } returns habitCounter
        coEvery { monthlyHabitDao.getHabitsForPeriod(habitCounter.period) } returns expectedHabitEntities
        every { monthlyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits

        val actualHabits = monthlyHabitRepositoryImpl.getHabitsForLastPeriod()

        coVerify(exactly = 1) { habitCounterRepository.getLastMonthlyCounter() }
        verify(exactly = 1) { monthlyHabitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { monthlyHabitDao.getHabitsForPeriod(habitCounter.period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runTest {
        val habit = FIRST_MONTHLY_HABIT
        val habitEntity = FIRST_MONTHLY_HABIT_ENTITY
        coJustRun { monthlyHabitDao.delete(habitEntity) }
        every { monthlyHabitMapper.fromDomain(habit) } returns habitEntity

        monthlyHabitRepositoryImpl.remove(habit)

        verify(exactly = 1) { monthlyHabitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { monthlyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runTest {
        val description = FIRST_DESCRIPTION
        coJustRun { monthlyHabitDao.deleteNotCompletedByDescription(description) }

        monthlyHabitRepositoryImpl.removeNotCompletedByDescription(description)

        coVerify(exactly = 1) { monthlyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runTest {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(id, description) }

        monthlyHabitRepositoryImpl.updateNotCompletedDescription(id, description)

        coVerify(exactly = 1) { monthlyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runTest {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }

        monthlyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)

        coVerify(exactly = 1) {
            monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
        }
    }
}