package com.hikarisource.smarthabits.data.repository

import com.hikarisource.smarthabits.data.local.DailyHabitDao
import com.hikarisource.smarthabits.data.mapper.DailyHabitMapper
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_ID
import com.hikarisource.smarthabits.util.TestData.HABIT_COUNTER_DAILY
import com.hikarisource.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_DAILY_HABIT_ENTITY
import com.hikarisource.smarthabits.util.TestData.SECOND_DESCRIPTION
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class DailyHabitRepositoryImplTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var dailyHabitDao: DailyHabitDao

    @RelaxedMockK
    private lateinit var dailyHabitMapper: DailyHabitMapper

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var dailyHabitRepositoryImpl: DailyHabitRepositoryImpl

    init {
        initMockKAnnotations()
    }

    @Test
    fun save() = runTest {
        val dailyHabit = FIRST_DAILY_HABIT
        val dailyHabitEntity = FIRST_DAILY_HABIT_ENTITY

        every { dailyHabitMapper.fromDomain(dailyHabit) } returns dailyHabitEntity
        coJustRun { dailyHabitDao.insert(dailyHabitEntity) }

        dailyHabitRepositoryImpl.save(dailyHabit)

        coVerify(exactly = 1) { dailyHabitMapper.fromDomain(dailyHabit) }
        coVerify(exactly = 1) { dailyHabitDao.insert(dailyHabitEntity) }
    }

    @Test
    fun getHabitsForLastPeriod() = runTest {
        val expectedHabits = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        val expectedHabitEntities = listOf(FIRST_DAILY_HABIT_ENTITY, SECOND_DAILY_HABIT_ENTITY)
        val habitCounter = HABIT_COUNTER_DAILY

        coEvery { habitCounterRepository.getLastDailyCounter() } returns HABIT_COUNTER_DAILY
        coEvery { dailyHabitDao.getHabitsForPeriod(habitCounter.period) } returns expectedHabitEntities
        every { dailyHabitMapper.toDomainList(expectedHabitEntities) } returns expectedHabits
        val actualHabits = dailyHabitRepositoryImpl.getHabitsForLastPeriod()

        coVerify(exactly = 1) { dailyHabitDao.getHabitsForPeriod(habitCounter.period) }
        Assert.assertEquals(expectedHabits, actualHabits)
    }

    @Test
    fun remove() = runTest {
        val habit = FIRST_DAILY_HABIT
        val habitEntity = FIRST_DAILY_HABIT_ENTITY

        coJustRun { dailyHabitDao.delete(habitEntity) }
        every { dailyHabitMapper.fromDomain(habit) } returns habitEntity

        dailyHabitRepositoryImpl.remove(habit)

        coVerify(exactly = 1) { dailyHabitDao.delete(habitEntity) }
    }

    @Test
    fun removeNotCompletedById() = runTest {
        val description = FIRST_DESCRIPTION
        coJustRun { dailyHabitDao.deleteNotCompletedByDescription(description) }

        dailyHabitRepositoryImpl.removeNotCompletedByDescription(description)

        coVerify(exactly = 1) { dailyHabitDao.deleteNotCompletedByDescription(description) }
    }

    @Test
    fun updateNotCompletedDescriptionById() = runTest {
        val id = FIRST_ID
        val description = FIRST_DESCRIPTION
        coJustRun { dailyHabitDao.updateNotCompletedDescription(id, description) }

        dailyHabitRepositoryImpl.updateNotCompletedDescription(id, description)

        coVerify(exactly = 1) { dailyHabitDao.updateNotCompletedDescription(id, description) }
    }

    @Test
    fun updateNotCompletedDescriptionByDescription() = runTest {
        val oldDescription = FIRST_DESCRIPTION
        val newDescription = SECOND_DESCRIPTION
        coJustRun { dailyHabitDao.updateNotCompletedDescription(oldDescription, newDescription) }
        dailyHabitRepositoryImpl.updateNotCompletedDescription(oldDescription, newDescription)
        coVerify(exactly = 1) {
            dailyHabitDao.updateNotCompletedDescription(
                oldDescription,
                newDescription
            )
        }
    }
}
