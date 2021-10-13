package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.HabitCounterDao
import com.sibela.smarthabits.data.mapper.HabitCounterMapper
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_DAILY_ENTITY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY_ENTITY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY_ENTITY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
import com.sibela.smarthabits.util.TestData.HABIT_COUNTER_YEARLY_ENTITY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
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

    @RelaxedMockK
    private lateinit var habitCounterMapper: HabitCounterMapper

    @InjectMockKs
    private lateinit var habitCounterRepositoryImpl: HabitCounterRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun getLastDailyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_DAILY
        val expectedHabitCounterEntity = HABIT_COUNTER_DAILY_ENTITY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounterEntity
        every { habitCounterMapper.toDomain(expectedHabitCounterEntity) } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        verify(exactly = 1) { habitCounterMapper.toDomain(expectedHabitCounterEntity) }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastWeeklyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_WEEKLY
        val expectedHabitCounterEntity = HABIT_COUNTER_WEEKLY_ENTITY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounterEntity
        every { habitCounterMapper.toDomain(expectedHabitCounterEntity) } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        verify(exactly = 1) { habitCounterMapper.toDomain(expectedHabitCounterEntity) }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastMonthlyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_MONTHLY
        val expectedHabitCounterEntity = HABIT_COUNTER_MONTHLY_ENTITY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounterEntity
        every { habitCounterMapper.toDomain(expectedHabitCounterEntity) } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        verify(exactly = 1) { habitCounterMapper.toDomain(expectedHabitCounterEntity) }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun getLastYearlyCounter() = runBlocking {
        val expectedHabitCounter = HABIT_COUNTER_YEARLY
        val expectedHabitCounterEntity = HABIT_COUNTER_YEARLY_ENTITY
        coEvery { habitCounterDao.getLastDailyHabitCounter() } returns expectedHabitCounterEntity
        every { habitCounterMapper.toDomain(expectedHabitCounterEntity) } returns expectedHabitCounter
        val actualHabitCounter = habitCounterRepositoryImpl.getLastDailyCounter()
        coVerify(exactly = 1) { habitCounterDao.getLastDailyHabitCounter() }
        verify(exactly = 1) { habitCounterMapper.toDomain(expectedHabitCounterEntity) }
        Assert.assertEquals(expectedHabitCounter, actualHabitCounter)
    }

    @Test
    fun insert() = runBlocking {
        val habitCounter = HABIT_COUNTER_DAILY
        val habitCounterEntity = HABIT_COUNTER_DAILY_ENTITY
        coJustRun { habitCounterDao.insert(habitCounterEntity) }
        every { habitCounterMapper.fromDomain(habitCounter) } returns habitCounterEntity
        habitCounterRepositoryImpl.insert(habitCounter)
        verify(exactly = 1) { habitCounterMapper.fromDomain(habitCounter) }
        coVerify(exactly = 1) { habitCounterDao.insert(habitCounterEntity) }
    }
}