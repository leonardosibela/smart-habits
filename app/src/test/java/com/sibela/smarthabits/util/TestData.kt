package com.sibela.smarthabits.util

import com.sibela.smarthabits.domain.model.*
import io.mockk.MockKAnnotations

object TestData {
    const val FIRST_DESCRIPTION = "Description"
    const val SECOND_DESCRIPTION = "Description two"
    const val FIRST_COMPLETED = true
    const val SECOND_COMPLETED = false
    const val FIRST_PERIOD = 1
    const val SECOND_PERIOD = 2

    val DAILY_PERIODICITY = Periodicity.DAILY
    val WEEKLY_PERIODICITY = Periodicity.WEEKLY
    val MONTHLY_PERIODICITY = Periodicity.MONTHLY
    val YEARLY_PERIODICITY = Periodicity.YEARLY

    val DAILY_PERIODICITY_AS_STRING = "DAILY"
    val WEEKLY_PERIODICITY_AS_STRING = "WEEKLY"
    val MONTHLY_PERIODICITY_AS_STRING = "MONTHLY"
    val YEARLY_PERIODICITY_AS_STRING = "YEARLY"

    val FIRST_HABIT = Habit(1, FIRST_DESCRIPTION, Periodicity.DAILY)
    val SECOND_HABIT = Habit(2, SECOND_DESCRIPTION, Periodicity.DAILY)

    val HABIT_DAILY = Habit(1, FIRST_DESCRIPTION, Periodicity.DAILY)
    val HABIT_WEEKLY = Habit(1, FIRST_DESCRIPTION, Periodicity.WEEKLY)
    val HABIT_MONTHLY = Habit(1, FIRST_DESCRIPTION, Periodicity.MONTHLY)
    val HABIT_YEARLY = Habit(1, FIRST_DESCRIPTION, Periodicity.YEARLY)

    val FIRST_DAILY_HABIT = DailyHabit(0, FIRST_DESCRIPTION, FIRST_COMPLETED, FIRST_PERIOD)
    val SECOND_DAILY_HABIT = DailyHabit(0, SECOND_DESCRIPTION, SECOND_COMPLETED, SECOND_PERIOD)

    val FIRST_WEEKLY_HABIT = WeeklyHabit(0, FIRST_DESCRIPTION, FIRST_COMPLETED, FIRST_PERIOD)
    val SECOND_WEEKLY_HABIT = WeeklyHabit(0, SECOND_DESCRIPTION, SECOND_COMPLETED, SECOND_PERIOD)

    val FIRST_MONTHLY_HABIT = MonthlyHabit(0, FIRST_DESCRIPTION, FIRST_COMPLETED, FIRST_PERIOD)
    val SECOND_MONTHLY_HABIT = MonthlyHabit(0, SECOND_DESCRIPTION, SECOND_COMPLETED, SECOND_PERIOD)

    val FIRST_YEARLY_HABIT = YearlyHabit(0, FIRST_DESCRIPTION, FIRST_COMPLETED, FIRST_PERIOD)
    val SECOND_YEARLY_HABIT = YearlyHabit(0, SECOND_DESCRIPTION, SECOND_COMPLETED, SECOND_PERIOD)

    val HABIT_COUNTER_DAILY = HabitCounter(1, Periodicity.DAILY, 1)
    val HABIT_COUNTER_WEEKLY = HabitCounter(1, Periodicity.WEEKLY, 1)
    val HABIT_COUNTER_MONTHLY = HabitCounter(1, Periodicity.MONTHLY, 1)
    val HABIT_COUNTER_YEARLY = HabitCounter(1, Periodicity.YEARLY, 1)
}

fun Any.initMockKAnnotations() {
    MockKAnnotations.init(this, relaxUnitFun = true)
}