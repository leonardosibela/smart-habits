package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.HabitCounterDao
import com.hikarisource.smarthabits.data.mapper.HabitCounterMapper
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_DAILY_ENTITY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_MONTHLY_ENTITY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_WEEKLY_ENTITY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_YEARLY
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_YEARLY_ENTITY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
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
    fun getLastDailyCounter() = runTest {
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
    fun getLastWeeklyCounter() = runTest {
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
    fun getLastMonthlyCounter() = runTest {
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
    fun getLastYearlyCounter() = runTest {
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
    fun insert() = runTest {
        val habitCounter = HABIT_COUNTER_DAILY
        val habitCounterEntity = HABIT_COUNTER_DAILY_ENTITY
        coJustRun { habitCounterDao.insert(habitCounterEntity) }
        every { habitCounterMapper.fromDomain(habitCounter) } returns habitCounterEntity

        habitCounterRepositoryImpl.insert(habitCounter)

        verify(exactly = 1) { habitCounterMapper.fromDomain(habitCounter) }
        coVerify(exactly = 1) { habitCounterDao.insert(habitCounterEntity) }
    }
}