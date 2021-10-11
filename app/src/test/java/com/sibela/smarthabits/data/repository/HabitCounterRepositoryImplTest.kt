package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.HabitCounterDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
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

class HabitCounterRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitCounterDao: HabitCounterDao

    @InjectMockKs
    private lateinit var habitCounterRepositoryImpl: HabitCounterRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun getLastDailyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_DAILY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastWeeklyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_WEEKLY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastMonthlyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_MONTHLY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastYearlyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_YEARLY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun insert() = runBlocking {
        val habitCounter = HABIT_COUNTER_DAILY
        coJustRun { habitCounterDao.insert(habitCounter) }
        habitCounterRepositoryImpl.insert(habitCounter)
        coVerify(exactly = 1) { habitCounterDao.insert(habitCounter) }
    }
}