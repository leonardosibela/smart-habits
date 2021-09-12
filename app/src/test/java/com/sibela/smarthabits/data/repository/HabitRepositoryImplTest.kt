package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.HabitDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_HABIT
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

class HabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var habitDao: HabitDao

    @InjectMockKs
    private lateinit var habitRepositoryImpl: HabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val habit = FIRST_HABIT
        coJustRun { habitDao.insert(habit) }
        habitRepositoryImpl.save(habit)
        coVerify(exactly = 1) { habitDao.insert(habit) }
    }

    @Test
    fun getAllHabitsThatAreDaily() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT, SECOND_HABIT)
        coEvery { habitDao.getAllThatAreDaily() } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreDaily()
        coVerify(exactly = 1) { habitDao.getAllThatAreDaily() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreWeekly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT, SECOND_HABIT)
        coEvery { habitDao.getAllThatAreWeekly() } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreWeekly()
        coVerify(exactly = 1) { habitDao.getAllThatAreWeekly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreMonthly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT, SECOND_HABIT)
        coEvery { habitDao.getAllThatAreMonthly() } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreMonthly()
        coVerify(exactly = 1) { habitDao.getAllThatAreMonthly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun getAllHabitsThatAreYearly() = runBlocking {
        val expectedHabits = listOf(FIRST_HABIT, SECOND_HABIT)
        coEvery { habitDao.getAllThatAreYearly() } returns expectedHabits
        val actualHabits = habitRepositoryImpl.getAllHabitsThatAreYearly()
        coVerify(exactly = 1) { habitDao.getAllThatAreYearly() }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun delete() = runBlocking {
        val habit = FIRST_HABIT
        coJustRun { habitDao.delete(habit) }
        habitRepositoryImpl.delete(habit)
        coVerify(exactly = 1) { habitDao.delete(habit) }
    }

    @Test
    fun edit() = runBlocking {
        val habit = FIRST_HABIT
        coJustRun { habitDao.update(habit) }
        habitRepositoryImpl.edit(habit)
        coVerify(exactly = 1) { habitDao.update(habit) }
    }
}