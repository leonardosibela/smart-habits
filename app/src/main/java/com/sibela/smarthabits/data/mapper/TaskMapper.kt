package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.domain.model.*

interface TaskMapper {
    fun toDailyTasks(tasks: List<Task>, completed: Boolean, period: Int): List<DailyTask>
    fun toDailyTask(task: Task, completed: Boolean, period: Int): DailyTask
    fun toWeeklyTasks(tasks: List<Task>, completed: Boolean, period: Int): List<WeeklyTask>
    fun toWeeklyTask(task: Task, completed: Boolean, period: Int): WeeklyTask
    fun toMonthlyTasks(tasks: List<Task>, completed: Boolean, period: Int): List<MonthlyTask>
    fun toMonthlyTask(task: Task, completed: Boolean, period: Int): MonthlyTask
    fun toYearlyTasks(tasks: List<Task>, completed: Boolean, period: Int): List<YearlyTask>
    fun toYearlyTask(task: Task, completed: Boolean, period: Int): YearlyTask
}