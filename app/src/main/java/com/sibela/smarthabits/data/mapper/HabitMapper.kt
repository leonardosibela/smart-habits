package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.domain.model.*

interface HabitMapper {
    fun toDailyHabits(habits: List<Habit>, completed: Boolean, period: Int): List<DailyHabit>
    fun toDailyHabit(habit: Habit, completed: Boolean, period: Int): DailyHabit
    fun toWeeklyHabits(habits: List<Habit>, completed: Boolean, period: Int): List<WeeklyHabit>
    fun toWeeklyHabit(habit: Habit, completed: Boolean, period: Int): WeeklyHabit
    fun toMonthlyHabits(habits: List<Habit>, completed: Boolean, period: Int): List<MonthlyHabit>
    fun toMonthlyHabit(habit: Habit, completed: Boolean, period: Int): MonthlyHabit
    fun toYearlyHabits(habits: List<Habit>, completed: Boolean, period: Int): List<YearlyHabit>
    fun toYearlyHabit(habit: Habit, completed: Boolean, period: Int): YearlyHabit
}