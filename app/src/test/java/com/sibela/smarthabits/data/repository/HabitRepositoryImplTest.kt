package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.data.local.HabitDao
import com.sibela.smarthabits.data.mapper.HabitMapper
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY_ENTITY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_MONTHLY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_MONTHLY_ENTITY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_WEEKLY_ENTITY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_YEARLY_ENTITY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY_ENTITY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_MONTHLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_MONTHLY_ENTITY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_WEEKLY_ENTITY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_YEARLY_ENTITY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitDao: HabitDao

    @RelaxedMockK
    private lateinit var habitMapper: HabitMapper

    @InjectMockKs
    private lateinit var habitRepositoryImpl: HabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val habit = FIRST_HABIT_DAILY
        val habitEntity = FIRST_HABIT_DAILY_ENTITY
        coJustRun { habitDao.insert(habitEntity) }
        every { habitMapper.fromDomain(habit) } returns habitEntity
        habitRepositoryImpl.save(habit)
        verify(exactly = 1) { habitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { habitDao.insert(habitEntity) }
    }

    @Test
    fun getAllHabitsThatAreDaily() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        val expectedHabitEntities = listOf(FIRST_HABIT_DAILY_ENTITY, SECOND_HABIT_DAILY_ENTITY)
        coEvery { habitDao.getAllThatAreDaily() } returns expectedHabitEntities
        every { habitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreDaily()
        verify(exactly = 1) { habitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { habitDao.getAllThatAreDaily() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreWeekly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT_WEEKLY, SECOND_HABIT_WEEKLY)
        val expectedHabitEntities = listOf(FIRST_HABIT_WEEKLY_ENTITY, SECOND_HABIT_WEEKLY_ENTITY)
        coEvery { habitDao.getAllThatAreWeekly() } returns expectedHabitEntities
        every { habitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreWeekly()
        verify(exactly = 1) { habitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { habitDao.getAllThatAreWeekly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreMonthly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT_MONTHLY, SECOND_HABIT_MONTHLY)
        val expectedHabitEntities = listOf(FIRST_HABIT_MONTHLY_ENTITY, SECOND_HABIT_MONTHLY_ENTITY)
        coEvery { habitDao.getAllThatAreMonthly() } returns expectedHabitEntities
        every { habitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreMonthly()
        verify(exactly = 1) { habitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { habitDao.getAllThatAreMonthly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreYearly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT_YEARLY, SECOND_HABIT_YEARLY)
        val expectedHabitEntities = listOf(FIRST_HABIT_YEARLY_ENTITY, SECOND_HABIT_YEARLY_ENTITY)
        coEvery { habitDao.getAllThatAreYearly() } returns expectedHabitEntities
        every { habitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreYearly()
        verify(exactly = 1) { habitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { habitDao.getAllThatAreYearly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun delete() = runBlocking {
        val habit = FIRST_HABIT_DAILY
        val habitEntity = FIRST_HABIT_DAILY_ENTITY
        coJustRun { habitDao.delete(habitEntity) }
        every { habitMapper.fromDomain(habit) } returns habitEntity
        habitRepositoryImpl.delete(habit)
        verify(exactly = 1) { habitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { habitDao.delete(habitEntity) }
    }

    @Test
    fun editHabitDescription() = runBlocking {
        val habit = FIRST_HABIT_DAILY
        coJustRun { habitDao.updateDescription(habit.id, habit.description) }
        habitRepositoryImpl.editHabitDescription(habit.id, habit.description)
        coVerify(exactly = 1) { habitDao.updateDescription(habit.id, habit.description) }
    }
}