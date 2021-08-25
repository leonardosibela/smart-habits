package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.*
import com.sibela.smarthabits.domain.repository.TaskRepository

class TaskRepositoryFake : TaskRepository {

    override suspend fun save(task: Task) {

    }

    override suspend fun getAllTasksThatAreDaily() = listOf(
        Task(0, "Read some pages of a book", Periodicity.DAILY),
        Task(0, "Exercise for at last 30 min", Periodicity.DAILY),
    )

    override suspend fun getAllTasksThatAreWeekly() = listOf(
        Task(0, "Read some pages of a book", Periodicity.WEEKLY),
        Task(0, "Exercise for at last 30 min", Periodicity.WEEKLY),
    )

    override suspend fun getAllTasksThatAreMonthly() = listOf(
        Task(0, "Read some pages of a book", Periodicity.MONTHLY),
        Task(0, "Exercise for at last 30 min", Periodicity.MONTHLY),
    )

    override suspend fun getAllTasksThatAreYearly() = listOf(
        Task(0, "Read some pages of a book", Periodicity.YEARLY),
        Task(0, "Exercise for at last 30 min", Periodicity.YEARLY),
    )

    override suspend fun delete(task: Task) {

    }

    override suspend fun edit(task: Task) {

    }
}