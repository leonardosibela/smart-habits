package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.domain.model.*

class HabitToPeriodicityHabitMapperImpl : HabitToPeriodicityHabitMapper {

    override fun toDailyHabits(habits: List<Habit>, completed: Boolean, period: Int) =
        habits.map { toDailyHabit(it, completed, period) }

    override fun toDailyHabit(habit: Habit, completed: Boolean, period: Int) = DailyHabit(
        description = habit.description,
        completed = completed,
        period = period
    )

    override fun toWeeklyHabits(habits: List<Habit>, completed: Boolean, period: Int) =
        habits.map { toWeeklyHabit(it, completed, period) }

    override fun toWeeklyHabit(habit: Habit, completed: Boolean, period: Int) = WeeklyHabit(
        description = habit.description,
        completed = completed,
        period = period
    )

    override fun toMonthlyHabits(habits: List<Habit>, completed: Boolean, period: Int) =
        habits.map { toMonthlyHabit(it, completed, period) }

    override fun toMonthlyHabit(habit: Habit, completed: Boolean, period: Int) = MonthlyHabit(
        description = habit.description,
        completed = completed,
        period = period
    )

    override fun toYearlyHabits(habits: List<Habit>, completed: Boolean, period: Int) =
        habits.map { toYearlyHabit(it, completed, period) }

    override fun toYearlyHabit(habit: Habit, completed: Boolean, period: Int) = YearlyHabit(
        description = habit.description,
        completed = completed,
        period = period
    )
}