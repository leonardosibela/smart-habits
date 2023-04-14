package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.DailyHabitDao
import com.hikarisource.smarthabits.data.mapper.DailyHabitMapper
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_ID
import com.hikarisource.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_DAILY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DailyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var dailyHabitDao: DailyHabitDao

    @RelaxedMockK
    private lateinit var dailyHabitMapper: DailyHabitMapper

    @InjectMockKs
    private lateinit var dailyHabitRepositoryImpl: DailyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val dailyHabit = FIRST_DAILY_HABIT
        val dailyHabitEntity = FIRST_DAILY_HABIT_ENTITY

        coJustRun { dailyHabitDao.insert(dailyHabitEntity) }
        every { dailyHabitMapper.fromDomain(dailyHabit) } returns dailyHabitEntity
        dailyHabitRepositoryImpl.save(dailyHabit)
        coVerify(exactly = 1) { dailyHabitDao.insert(dailyHabitEntity) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val expectedHabits = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        val expectedHabitEntities = listOf(FIRST_DAILY_HABIT_ENTITY, SECOND_DAILY_HABIT_ENTITY)

        coEvery { dailyHabitDao.getHabitsForPeriod(period) } returns expectedHabitEntities
        every { dailyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = dailyHabitRepositoryImpl.getHabitsForLastPeriod(period)
        coVerify(exactly = 1) { dailyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_DAILY_HABIT
        val habitEntity = FIRST_DAILY_HABIT_ENTITY
        coJustRun { dailyHabitDao.delete(habitEntity) }
        every { dailyHabitMapper.fromDomain(habit) } returns habitEntity
        dailyHabitRepositoryImpl.remove(habit)
        coVerify(exactly = 1) { dailyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val description = FIRST_DESCRIPTION
        coJustRun { dailyHabitDao.deleteNotCompletedByDescription(description) }
        dailyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { dailyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescriptionById() = runBlocking {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { dailyHabitDao.updateNotCompletedDescription(id, description) }
        dailyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { dailyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { dailyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        dailyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            dailyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}