package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.util.TestData.FIRST_COMPLETED
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_MONTHLY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_PERIOD
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_COMPLETED
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_MONTHLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_YEARLY
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_PERIOD
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import org.junit.Assert
import org.junit.Test

class HabitToPeriodicityHabitMapperImplTest {

    private val mapper = HabitToPeriodicityHabitMapperImpl()

    @Test
    fun toDailyHabits() {
        val actualDailyHabits = mapper.toDailyHabits(
            listOf(FIRST_HABIT_DAILY, FIRST_HABIT_DAILY), FIRST_COMPLETED, FIRST_PERIOD
        )

        val firstDailyHabit = FIRST_DAILY_HABIT.copy().apply { id = 0 }
        val secondDailyHabit = FIRST_DAILY_HABIT.copy().apply { id = 0 }
        val expectedDailyHabits = arrayOf(firstDailyHabit, secondDailyHabit)

        Assert.assertArrayEquals(expectedDailyHabits, actualDailyHabits.toTypedArray())
    }

    @Test
    fun toDailyHabit() {
        val expectedFirstDailyHabit = FIRST_DAILY_HABIT.copy().apply { id = 0 }
        val actualFirstDailyHabit =
            mapper.toDailyHabit(FIRST_HABIT_DAILY, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(expectedFirstDailyHabit, actualFirstDailyHabit)

        val expectedSecondDailyHabit = SECOND_DAILY_HABIT.copy().apply { id = 0 }
        val actualSecondDailyHabit =
            mapper.toDailyHabit(SECOND_HABIT_DAILY, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(expectedSecondDailyHabit, actualSecondDailyHabit)
    }

    @Test
    fun toWeeklyHabits() {
        val actualWeeklyHabits = mapper.toWeeklyHabits(
            listOf(FIRST_HABIT_WEEKLY, FIRST_HABIT_WEEKLY), FIRST_COMPLETED, FIRST_PERIOD
        )

        val firstWeeklyHabit = FIRST_WEEKLY_HABIT.copy().apply { id = 0 }
        val secondWeeklyHabit = FIRST_WEEKLY_HABIT.copy().apply { id = 0 }
        val expectedWeeklyHabits = arrayOf(firstWeeklyHabit, secondWeeklyHabit)

        Assert.assertArrayEquals(expectedWeeklyHabits, actualWeeklyHabits.toTypedArray())
    }

    @Test
    fun toWeeklyHabit() {
        val expectedFirstWeeklyHabit = FIRST_WEEKLY_HABIT.copy().apply { id = 0 }
        val actualFirstWeeklyHabit =
            mapper.toWeeklyHabit(FIRST_HABIT_WEEKLY, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(expectedFirstWeeklyHabit, actualFirstWeeklyHabit)

        val expectedSecondWeeklyHabit = SECOND_WEEKLY_HABIT.copy().apply { id = 0 }
        val actualSecondWeeklyHabit =
            mapper.toWeeklyHabit(SECOND_HABIT_WEEKLY, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(expectedSecondWeeklyHabit, actualSecondWeeklyHabit)
    }

    @Test
    fun toMonthlyHabits() {
        val actualMonthlyHabits = mapper.toMonthlyHabits(
            listOf(FIRST_HABIT_MONTHLY, FIRST_HABIT_MONTHLY), FIRST_COMPLETED, FIRST_PERIOD
        )

        val firstMonthlyHabit = FIRST_MONTHLY_HABIT.copy().apply { id = 0 }
        val secondMonthlyHabit = FIRST_MONTHLY_HABIT.copy().apply { id = 0 }
        val expectedMonthlyHabits = arrayOf(firstMonthlyHabit, secondMonthlyHabit)

        Assert.assertArrayEquals(expectedMonthlyHabits, actualMonthlyHabits.toTypedArray())
    }

    @Test
    fun toMonthlyHabit() {
        val expectedFirstMonthlyHabit = FIRST_MONTHLY_HABIT.copy().apply { id = 0 }
        val actualFirstMonthlyHabit =
            mapper.toMonthlyHabit(FIRST_HABIT_MONTHLY, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(expectedFirstMonthlyHabit, actualFirstMonthlyHabit)

        val expectedSecondMonthlyHabit = SECOND_MONTHLY_HABIT.copy().apply { id = 0 }
        val actualSecondMonthlyHabit =
            mapper.toMonthlyHabit(SECOND_HABIT_MONTHLY, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(expectedSecondMonthlyHabit, actualSecondMonthlyHabit)
    }

    @Test
    fun toYearlyHabits() {
        val actualYearlyHabits = mapper.toYearlyHabits(
            listOf(FIRST_HABIT_YEARLY, FIRST_HABIT_YEARLY), FIRST_COMPLETED, FIRST_PERIOD
        )

        val firstYearlyHabit = FIRST_YEARLY_HABIT.copy().apply { id = 0 }
        val secondYearlyHabit = FIRST_YEARLY_HABIT.copy().apply { id = 0 }
        val expectedYearlyHabits = arrayOf(firstYearlyHabit, secondYearlyHabit)

        Assert.assertArrayEquals(expectedYearlyHabits, actualYearlyHabits.toTypedArray())
    }

    @Test
    fun toYearlyHabit() {
        val expectedFirstYearlyHabit = FIRST_YEARLY_HABIT.copy().apply { id = 0 }
        val actualFirstYearlyHabit =
            mapper.toYearlyHabit(FIRST_HABIT_YEARLY, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(expectedFirstYearlyHabit, actualFirstYearlyHabit)

        val expectedSecondYearlyHabit = SECOND_YEARLY_HABIT.copy().apply { id = 0 }
        val actualSecondYearlyHabit =
            mapper.toYearlyHabit(SECOND_HABIT_YEARLY, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(expectedSecondYearlyHabit, actualSecondYearlyHabit)
    }
}