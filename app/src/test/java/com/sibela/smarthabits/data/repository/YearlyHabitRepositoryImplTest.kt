package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.local.YearlyHabitDao
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
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

class YearlyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var yearlyHabitDao: YearlyHabitDao

    @InjectMockKs
    private lateinit var yearlyHabitRepositoryImpl: YearlyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coJustRun { yearlyHabitDao.insert(yearlyHabit) }
        yearlyHabitRepositoryImpl.save(yearlyHabit)
        coVerify(exactly = 1) { yearlyHabitDao.insert(yearlyHabit) }
    }

    @Test
    fun getHabitsFoPeriod() = runBlocking {
        val period = 1
        val expectedHabits = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        coEvery { yearlyHabitDao.getHabitsForPeriod(period) } returns expectedHabits
        val actualHabits = yearlyHabitRepositoryImpl.getHabitsForPeriod(period)
        coVerify(exactly = 1) { yearlyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_YEARLY_HABIT
        coJustRun { yearlyHabitDao.delete(habit) }
        yearlyHabitRepositoryImpl.remove(habit)
        coVerify(exactly = 1) { yearlyHabitDao.delete(habit) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val id = 1
        coJustRun { yearlyHabitDao.deleteNotCompletedById(id) }
        yearlyHabitRepositoryImpl.removeNotCompletedById(id)
        coVerify(exactly = 1) { yearlyHabitDao.deleteNotCompletedById(id) }
    }
}