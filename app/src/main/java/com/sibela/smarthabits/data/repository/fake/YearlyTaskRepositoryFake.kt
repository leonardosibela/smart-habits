package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.YearlyTask
import com.sibela.smarthabits.domain.repository.YearlyTaskRepository

class YearlyTaskRepositoryFake : YearlyTaskRepository {

    override suspend fun save(yearlyTask: YearlyTask) {

    }

    override suspend fun getTasksForPeriod(period: Int) = listOf(
        YearlyTask(0, "Read some pages of a book", false, 1),
        YearlyTask(0, "Exercise for at last 30 min", false, 1),
    )
}