package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.DailyHabitDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
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

    @InjectMockKs
    private lateinit var dailyHabitRepositoryImpl: DailyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { dailyHabitDao.insert(dailyHabit) }
        dailyHabitRepositoryImpl.save(dailyHabit)
        coVerify(exactly = 1) { dailyHabitDao.insert(dailyHabit) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val period = 1
        val expectedHabits = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        coEvery { dailyHabitDao.getHabitsForPeriod(period) } returns expectedHabits
        val actualHabits = dailyHabitRepositoryImpl.getHabitsForPeriod(period)
        coVerify(exactly = 1) { dailyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_DAILY_HABIT
        coJustRun { dailyHabitDao.delete(habit) }
        dailyHabitRepositoryImpl.remove(habit)
        coVerify(exactly = 1) { dailyHabitDao.delete(habit) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val description = "Description"
        coJustRun { dailyHabitDao.deleteNotCompletedByDescription(description) }
        dailyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { dailyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescriptionById() = runBlocking {
        val id = 1
        val description = "Description"
        coJustRun { dailyHabitDao.updateNotCompletedDescription(id, description) }
        dailyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { dailyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = "Old description"
        val newDescription = "New description"
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