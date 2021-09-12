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
        val description = "Description"
        coJustRun { monthlyHabitDao.deleteNotCompletedByDescription(description) }
        monthlyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { monthlyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runBlocking {
        val id = 1
        val description = "Description"
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(id, description) }
        monthlyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { monthlyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = "Old description"
        val newDescription = "New description"
        coJustRun { monthlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        monthlyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            monthlyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}