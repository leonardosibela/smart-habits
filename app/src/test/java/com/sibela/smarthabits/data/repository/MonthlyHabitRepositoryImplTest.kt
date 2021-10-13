package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.MonthlyHabitDao
import com.sibela.smarthabits.data.mapper.MonthlyHabitMapper
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.sibela.smarthabits.util.TestData.FIRST_ID
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT_ENTITY
import com.sibela.smarthabits.util.TestData.FIRST_PERIOD
import com.sibela.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT_ENTITY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MonthlyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var monthlyHabitDao: MonthlyHabitDao

    @RelaxedMockK
    private lateinit var monthlyHabitMapper: MonthlyHabitMapper

    @InjectMockKs
    private lateinit var monthlyHabitRepositoryImpl: MonthlyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        val monthlyHabitEntity = FIRST_MONTHLY_HABIT_ENTITY
        coJustRun { monthlyHabitDao.insert(monthlyHabitEntity) }
        every { monthlyHabitMapper.fromDomain(monthlyHabit) } returns monthlyHabitEntity
        monthlyHabitRepositoryImpl.save(monthlyHabit)
        verify(exactly = 1) { monthlyHabitMapper.fromDomain(monthlyHabit) }
        coVerify(exactly = 1) { monthlyHabitDao.insert(monthlyHabitEntity) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val period = FIRST_PERIOD
        val expectedHabits = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        val expectedHabitEntities = listOf(FIRST_MONTHLY_HABIT_ENTITY, SECOND_MONTHLY_HABIT_ENTITY)
        coEvery { monthlyHabitDao.getHabitsForPeriod(period) } returns expectedHabitEntities
        every { monthlyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = monthlyHabitRepositoryImpl.getHabitsForPeriod(period)
        verify(exactly = 1) { monthlyHabitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { monthlyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_MONTHLY_HABIT
        val habitEntity = FIRST_MONTHLY_HABIT_ENTITY
        coJustRun { monthlyHabitDao.delete(habitEntity) }
        every { monthlyHabitMapper.fromDomain(habit) } returns habitEntity
        monthlyHabitRepositoryImpl.remove(habit)
        verify(exactly = 1) { monthlyHabitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { monthlyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val description = FIRST_DESCRIPTION
        coJustRun { monthlyHabitDao.deleteNotCompletedByDescription(description) }
        monthlyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { monthlyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runBlocking {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(id, description) }
        monthlyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { monthlyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        monthlyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            monthlyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}