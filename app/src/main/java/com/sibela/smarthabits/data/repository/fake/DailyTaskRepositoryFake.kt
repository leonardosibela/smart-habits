package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.DailyTask
import com.sibela.smarthabits.domain.repository.DailyTaskRepository

class DailyTaskRepositoryFake : DailyTaskRepository {

    override suspend fun save(dailyTask: DailyTask) {

    }

    override suspend fun getTaskFoPeriod(period: Int) =
        listOf(
            DailyTask(0, "Read some pages of a book", false, 1),
            DailyTask(0, "Exercise for at last 30 min", false, 1),
        )

    override suspend fun remove(dailyTask: DailyTask) {

    }
}