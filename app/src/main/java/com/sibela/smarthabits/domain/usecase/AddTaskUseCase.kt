package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.model.*
import com.sibela.smarthabits.domain.repository.*

class AddTaskUseCase(
    private val taskRepository: TaskRepository,
    private val taskCounterRepository: TaskCounterRepository,
    private val dailyTaskRepository: DailyTaskRepository,
    private val weeklyTaskRepository: WeeklyTaskRepository,
    private val monthlyTaskRepository: MonthlyTaskRepository,
    private val yearlyTaskRepository: YearlyTaskRepository,
) {

    internal suspend operator fun invoke(description: String, periodicity: Periodicity) {
        taskRepository.save(Task(description = description, periodicity = periodicity))
        when (periodicity) {
            Periodicity.DAILY -> saveDailyTask(description)
            Periodicity.WEEKLY -> saveWeeklyTask(description)
            Periodicity.MONTHLY -> saveMonthlyTask(description)
            Periodicity.YEARLY -> saveYearlyTask(description)
        }
    }

    private suspend fun saveDailyTask(description: String) {
        val lastDailyCounter = taskCounterRepository.getLastDailyCounter()
        val dailyTask = DailyTask(
            description = description,
            completed = false,
            period = lastDailyCounter.period
        )
        dailyTaskRepository.save(dailyTask)
    }

    private suspend fun saveWeeklyTask(description: String) {
        val lastWeeklyCounter = taskCounterRepository.getLastWeeklyCounter()
        val weeklyTask = WeeklyTask(
            description = description,
            completed = false,
            period = lastWeeklyCounter.period
        )
        weeklyTaskRepository.save(weeklyTask)
    }

    private suspend fun saveMonthlyTask(description: String) {
        val lastWeeklyCounter = taskCounterRepository.getLastMonthlyCounter()
        val monthlyTask = MonthlyTask(
            description = description,
            completed = false,
            period = lastWeeklyCounter.period
        )
        monthlyTaskRepository.save(monthlyTask)
    }

    private suspend fun saveYearlyTask(description: String) {
        val lastYearlyCounter = taskCounterRepository.getLastYearlyCounter()
        val yearlyTask = YearlyTask(
            description = description,
            completed = false,
            period = lastYearlyCounter.period
        )
        yearlyTaskRepository.save(yearlyTask)
    }
}