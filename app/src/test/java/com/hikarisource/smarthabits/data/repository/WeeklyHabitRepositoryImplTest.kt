package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.WeeklyHabitDao
import com.hikarisource.smarthabits.data.mapper.WeeklyHabitMapper
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_ID
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.hikarisource.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_WEEKLY_HABIT_ENTITY
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
class WeeklyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var weeklyHabitDao: WeeklyHabitDao

    @RelaxedMockK
    private lateinit var weeklyHabitMapper: WeeklyHabitMapper

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var weeklyHabitRepositoryImpl: WeeklyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runTest {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        val weeklyHabitEntity = FIRST_WEEKLY_HABIT_ENTITY
        coJustRun { weeklyHabitDao.insert(weeklyHabitEntity) }
        every { weeklyHabitMapper.fromDomain(weeklyHabit) } returns weeklyHabitEntity

        weeklyHabitRepositoryImpl.save(weeklyHabit)

        verify(exactly = 1) { weeklyHabitMapper.fromDomain(weeklyHabit) }
        coVerify(exactly = 1) { weeklyHabitDao.insert(weeklyHabitEntity) }
    }

    @Test
    fun getHabitsForPeriod() = runTest {
        val habitCounter = HABIT_COUNTER_WEEKLY
        val expectedHabits = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        val expectedHabitEntities = listOf(FIRST_WEEKLY_HABIT_ENTITY, SECOND_WEEKLY_HABIT_ENTITY)

        coEvery { habitCounterRepository.getLastWeeklyCounter() } returns habitCounter
        coEvery { weeklyHabitDao.getHabitsForPeriod(habitCounter.period) } returns expectedHabitEntities
        every { weeklyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits

        val actualHabits = weeklyHabitRepositoryImpl.getHabitsForLastPeriod()

        verify(exactly = 1) { weeklyHabitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { weeklyHabitDao.getHabitsForPeriod(habitCounter.period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runTest {
        val habit = FIRST_WEEKLY_HABIT
        val habitEntity = FIRST_WEEKLY_HABIT_ENTITY
        coJustRun { weeklyHabitDao.delete(habitEntity) }
        every { weeklyHabitMapper.fromDomain(habit) } returns habitEntity

        weeklyHabitRepositoryImpl.remove(habit)

        verify(exactly = 1) { weeklyHabitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { weeklyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runTest {
        val description = FIRST_DESCRIPTION
        coJustRun { weeklyHabitDao.deleteNotCompletedByDescription(description) }

        weeklyHabitRepositoryImpl.removeNotCompletedByDescription(description)

        coVerify(exactly = 1) { weeklyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runTest {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { weeklyHabitDao.updateNotCompletedDescription(id, description) }

        weeklyHabitRepositoryImpl.updateNotCompletedDescription(id, description)

        coVerify(exactly = 1) { weeklyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runTest {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { weeklyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }

        weeklyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)

        coVerify(exactly = 1) {
            weeklyHabitDao.updateNotCompletedDescription(oldDescription, newDescription)
        }
    }
}