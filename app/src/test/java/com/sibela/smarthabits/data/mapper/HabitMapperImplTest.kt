package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.TestData.FIRST_COMPLETED
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_PERIOD
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_COMPLETED
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_PERIOD
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import org.junit.Assert
import org.junit.Test

class HabitMapperImplTest {

    private val mapper = HabitMapperImpl()

    @Test
    fun toDailyHabits() {
        val firstDailyHabits = mapper.toDailyHabits(
            listOf(
                TestData.FIRST_HABIT,
                TestData.FIRST_HABIT
            ),
            FIRST_COMPLETED,
            FIRST_PERIOD
        )
        Assert.assertArrayEquals(
            arrayOf(FIRST_DAILY_HABIT, FIRST_DAILY_HABIT),
            firstDailyHabits.toTypedArray()
        )
    }

    @Test
    fun toDailyHabit() {
        val firstDailyHabit =
            mapper.toDailyHabit(TestData.FIRST_HABIT, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(FIRST_DAILY_HABIT, firstDailyHabit)

        val secondDailyHabit =
            mapper.toDailyHabit(TestData.SECOND_HABIT, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(SECOND_DAILY_HABIT, secondDailyHabit)
    }

    @Test
    fun toWeeklyHabits() {
        val firstWeeklyHabits = mapper.toWeeklyHabits(
            listOf(
                TestData.FIRST_HABIT,
                TestData.FIRST_HABIT
            ),
            FIRST_COMPLETED,
            FIRST_PERIOD
        )
        Assert.assertArrayEquals(
            arrayOf(FIRST_WEEKLY_HABIT, FIRST_WEEKLY_HABIT),
            firstWeeklyHabits.toTypedArray()
        )
    }

    @Test
    fun toWeeklyHabit() {
        val firstWeeklyHabit =
            mapper.toWeeklyHabit(TestData.FIRST_HABIT, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(FIRST_WEEKLY_HABIT, firstWeeklyHabit)

        val secondWeeklyHabit =
            mapper.toWeeklyHabit(TestData.SECOND_HABIT, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(SECOND_WEEKLY_HABIT, secondWeeklyHabit)
    }

    @Test
    fun toMonthlyHabits() {
        val firstMonthlyHabits = mapper.toMonthlyHabits(
            listOf(
                TestData.FIRST_HABIT,
                TestData.FIRST_HABIT
            ),
            FIRST_COMPLETED,
            FIRST_PERIOD
        )
        Assert.assertArrayEquals(
            arrayOf(FIRST_MONTHLY_HABIT, FIRST_MONTHLY_HABIT),
            firstMonthlyHabits.toTypedArray()
        )
    }

    @Test
    fun toMonthlyHabit() {
        val firstMonthlyHabit =
            mapper.toMonthlyHabit(TestData.FIRST_HABIT, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(FIRST_MONTHLY_HABIT, firstMonthlyHabit)

        val secondMonthlyHabit =
            mapper.toMonthlyHabit(TestData.SECOND_HABIT, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(SECOND_MONTHLY_HABIT, secondMonthlyHabit)
    }

    @Test
    fun toYearlyHabits() {
        val firstYearlyHabits = mapper.toYearlyHabits(
            listOf(
                TestData.FIRST_HABIT,
                TestData.FIRST_HABIT
            ),
            FIRST_COMPLETED,
            FIRST_PERIOD
        )
        Assert.assertArrayEquals(
            arrayOf(FIRST_YEARLY_HABIT, FIRST_YEARLY_HABIT),
            firstYearlyHabits.toTypedArray()
        )
    }

    @Test
    fun toYearlyHabit() {
        val firstYearlyHabit =
            mapper.toYearlyHabit(TestData.FIRST_HABIT, FIRST_COMPLETED, FIRST_PERIOD)
        Assert.assertEquals(FIRST_YEARLY_HABIT, firstYearlyHabit)

        val secondYearlyHabit =
            mapper.toYearlyHabit(TestData.SECOND_HABIT, SECOND_COMPLETED, SECOND_PERIOD)
        Assert.assertEquals(SECOND_YEARLY_HABIT, secondYearlyHabit)
    }
}