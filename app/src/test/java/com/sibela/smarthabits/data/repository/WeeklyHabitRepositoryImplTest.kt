package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.WeeklyHabitDao
import com.sibela.smarthabits.data.mapper.WeeklyHabitMapper
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.sibela.smarthabits.util.TestData.FIRST_ID
import com.sibela.smarthabits.util.TestData.FIRST_PERIOD
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT_ENTITY
import com.sibela.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT_ENTITY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WeeklyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var weeklyHabitDao: WeeklyHabitDao

    @RelaxedMockK
    private lateinit var weeklyHabitMapper: WeeklyHabitMapper

    @InjectMockKs
    private lateinit var weeklyHabitRepositoryImpl: WeeklyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        val weeklyHabitEntity = FIRST_WEEKLY_HABIT_ENTITY
        coJustRun { weeklyHabitDao.insert(weeklyHabitEntity) }
        every { weeklyHabitMapper.fromDomain(weeklyHabit) } returns weeklyHabitEntity
        weeklyHabitRepositoryImpl.save(weeklyHabit)
        verify(exactly = 1) { weeklyHabitMapper.fromDomain(weeklyHabit) }
        coVerify(exactly = 1) { weeklyHabitDao.insert(weeklyHabitEntity) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val period = FIRST_PERIOD
        val expectedHabits = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        val expectedHabitEntities = listOf(FIRST_WEEKLY_HABIT_ENTITY, SECOND_WEEKLY_HABIT_ENTITY)
        coEvery { weeklyHabitDao.getHabitsForPeriod(period) } returns expectedHabitEntities
        every { weeklyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = weeklyHabitRepositoryImpl.getHabitsForPeriod(period)
        verify(exactly = 1) { weeklyHabitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { weeklyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_WEEKLY_HABIT
        val habitEntity = FIRST_WEEKLY_HABIT_ENTITY
        coJustRun { weeklyHabitDao.delete(habitEntity) }
        every { weeklyHabitMapper.fromDomain(habit) } returns habitEntity
        weeklyHabitRepositoryImpl.remove(habit)
        verify(exactly = 1) { weeklyHabitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { weeklyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val description = FIRST_DESCRIPTION
        coJustRun { weeklyHabitDao.deleteNotCompletedByDescription(description) }
        weeklyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { weeklyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runBlocking {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { weeklyHabitDao.updateNotCompletedDescription(id, description) }
        weeklyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { weeklyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { weeklyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        weeklyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            weeklyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}