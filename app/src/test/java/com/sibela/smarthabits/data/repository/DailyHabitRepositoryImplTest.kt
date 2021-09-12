package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.DailyHabitDao
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
        val id = 1
        coJustRun { dailyHabitDao.deleteNotCompletedById(id) }
        dailyHabitRepositoryImpl.removeNotCompletedById(id)
        coVerify(exactly = 1) { dailyHabitDao.deleteNotCompletedById(id) }
    }
}