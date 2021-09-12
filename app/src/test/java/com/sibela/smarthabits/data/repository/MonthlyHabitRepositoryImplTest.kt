package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.MonthlyHabitDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
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

class MonthlyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var monthlyHabitDao: MonthlyHabitDao

    @InjectMockKs
    private lateinit var monthlyHabitRepositoryImpl: MonthlyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coJustRun { monthlyHabitDao.insert(monthlyHabit) }
        monthlyHabitRepositoryImpl.save(monthlyHabit)
        coVerify(exactly = 1) { monthlyHabitDao.insert(monthlyHabit) }
    }

    @Test
    fun getHabitsForPeriod() = runBlocking {
        val period = 1
        val expectedHabits = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        coEvery { monthlyHabitDao.getHabitsForPeriod(period) } returns expectedHabits
        val actualHabits = monthlyHabitRepositoryImpl.getHabitsForPeriod(period)
        coVerify(exactly = 1) { monthlyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_MONTHLY_HABIT
        coJustRun { monthlyHabitDao.delete(habit) }
        monthlyHabitRepositoryImpl.remove(habit)
        coVerify(exactly = 1) { monthlyHabitDao.delete(habit) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val id = 1
        coJustRun { monthlyHabitDao.deleteNotCompletedById(id) }
        monthlyHabitRepositoryImpl.removeNotCompletedById(id)
        coVerify(exactly = 1) { monthlyHabitDao.deleteNotCompletedById(id) }
    }
}