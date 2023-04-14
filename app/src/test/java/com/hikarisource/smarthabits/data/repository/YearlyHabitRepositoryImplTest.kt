package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.YearlyHabitDao
import com.hikarisource.smarthabits.data.mapper.YearlyHabitMapper
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_ID
import com.hikarisource.smarthabits.util.TestData.FIRST_PERIOD
import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_YEARLY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.*
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

    @RelaxedMockK
    private lateinit var yearlyHabitMapper: YearlyHabitMapper

    @InjectMockKs
    private lateinit var yearlyHabitRepositoryImpl: YearlyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runBlocking {
        val yearlyHabit = FIRST_YEARLY_HABIT
        val yearlyHabitEntity = FIRST_YEARLY_HABIT_ENTITY
        coJustRun { yearlyHabitDao.insert(yearlyHabitEntity) }
        every { yearlyHabitMapper.fromDomain(yearlyHabit) } returns yearlyHabitEntity
        yearlyHabitRepositoryImpl.save(yearlyHabit)
        verify(exactly = 1) { yearlyHabitMapper.fromDomain(yearlyHabit) }
        coVerify(exactly = 1) { yearlyHabitDao.insert(yearlyHabitEntity) }
    }

    @Test
    fun getHabitsFoPeriod() = runBlocking {
        val period = FIRST_PERIOD
        val expectedHabits = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        val expectedHabitEntities = listOf(FIRST_YEARLY_HABIT_ENTITY, SECOND_YEARLY_HABIT_ENTITY)
        coEvery { yearlyHabitDao.getHabitsForPeriod(period) } returns expectedHabitEntities
        every { yearlyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = yearlyHabitRepositoryImpl.getHabitsForPeriod(period)
        verify(exactly = 1) { yearlyHabitMapper.toDomainList(expectedHabitEntities) }
        coVerify(exactly = 1) { yearlyHabitDao.getHabitsForPeriod(period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runBlocking {
        val habit = FIRST_YEARLY_HABIT
        val habitEntity = FIRST_YEARLY_HABIT_ENTITY
        coJustRun { yearlyHabitDao.delete(habitEntity) }
        every { yearlyHabitMapper.fromDomain(habit) } returns habitEntity
        yearlyHabitRepositoryImpl.remove(habit)
        verify { yearlyHabitMapper.fromDomain(habit) }
        coVerify(exactly = 1) { yearlyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runBlocking {
        val description = FIRST_DESCRIPTION
        coJustRun { yearlyHabitDao.deleteNotCompletedByDescription(description) }
        yearlyHabitRepositoryImpl.removeNotCompletedByDescription(description)
        coVerify(exactly = 1) { yearlyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescription() = runBlocking {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { yearlyHabitDao.updateNotCompletedDescription(id, description) }
        yearlyHabitRepositoryImpl.updateNotCompletedDescription(id, description)
        coVerify(exactly = 1) { yearlyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runBlocking {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { yearlyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        yearlyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            yearlyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}