package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.MonthlyTask
import com.sibela.smarthabits.domain.repository.MonthlyTaskRepository

class MonthlyTaskRepositoryFake : MonthlyTaskRepository {

    override suspend fun save(monthlyTask: MonthlyTask) {

    }

    override suspend fun getTaskForPeriod(period: Int) = listOf(
        MonthlyTask(0, "Read some pages of a book", false, 1),
        MonthlyTask(0, "Exercise for at last 30 min", false, 1),
    )
}