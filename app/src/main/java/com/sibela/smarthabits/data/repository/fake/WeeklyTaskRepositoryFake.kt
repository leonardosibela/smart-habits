package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.WeeklyTask
import com.sibela.smarthabits.domain.repository.WeeklyTaskRepository

class WeeklyTaskRepositoryFake : WeeklyTaskRepository {

    override suspend fun save(weeklyTask: WeeklyTask) {

    }

    override suspend fun getTaskForPeriod(period: Int) = listOf(
        WeeklyTask(0, "Read some pages of a book", false, 1),
        WeeklyTask(0, "Exercise for at last 30 min", false, 1),
    )
}