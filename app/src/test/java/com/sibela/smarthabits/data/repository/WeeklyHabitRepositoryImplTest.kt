package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.WeeklyHabitDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
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

class WeeklyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var weeklyHabitDao: WeeklyHabitDao

    @InjectMockKs
    private lateinit var weeklyHabitRepositoryImpl: WeeklyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coJustRun { weeklyHabitDao.insert(weeklyHabit) }
        weeklyHabitRepositoryImpl.save(weeklyHabit)
        coVerify(exactly = 1) { weeklyHabitDao.insert(weeklyHabit) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val period = 1
        val expectedHabits = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        coEvery { weeklyHabitDao.getHabitsForPeriod(period) } returns expectedHabits
        val actualHabits = weeklyHabitRepositoryImpl.getHabitsForPeriod(period)
        coVerify(exactly = 1) { weeklyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_WEEKLY_HABIT
        coJustRun { weeklyHabitDao.delete(habit) }
        weeklyHabitRepositoryImpl.remove(habit)
        coVerify(exactly = 1) { weeklyHabitDao.delete(habit) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val id = 1
        coJustRun { weeklyHabitDao.deleteNotCompletedById(id) }
        weeklyHabitRepositoryImpl.removeNotCompletedById(id)
        coVerify(exactly = 1) { weeklyHabitDao.deleteNotCompletedById(id) }
    }
}