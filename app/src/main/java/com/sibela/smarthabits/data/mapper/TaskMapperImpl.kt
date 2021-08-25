package com.sibela.smarthabits.data.mapper

import com.sibela.smarthabits.domain.model.*

class TaskMapperImpl : TaskMapper {

    override fun toDailyTasks(tasks: List<Task>, completed: Boolean, period: Int) =
        tasks.map { toDailyTask(it, completed, period) }

    override fun toDailyTask(task: Task, completed: Boolean, period: Int) = DailyTask(
        description = task.description,
        completed = completed,
        period = period
    )

    override fun toWeeklyTasks(tasks: List<Task>, completed: Boolean, period: Int) =
        tasks.map { toWeeklyTask(it, completed, period) }

    override fun toWeeklyTask(task: Task, completed: Boolean, period: Int) = WeeklyTask(
        description = task.description,
        completed = completed,
        period = period
    )

    override fun toMonthlyTasks(tasks: List<Task>, completed: Boolean, period: Int) =
        tasks.map { toMonthlyTask(it, completed, period) }

    override fun toMonthlyTask(task: Task, completed: Boolean, period: Int) = MonthlyTask(
        description = task.description,
        completed = completed,
        period = period
    )

    override fun toYearlyTasks(tasks: List<Task>, completed: Boolean, period: Int) =
        tasks.map { toYearlyTask(it, completed, period) }

    override fun toYearlyTask(task: Task, completed: Boolean, period: Int) = YearlyTask(
        description = task.description,
        completed = completed,
        period = period
    )
}