package com.sibela.smarthabits.data.repository

import com.sibela.smarthabits.domain.model.HabitCounter
import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class HabitCounterRepositoryFake : HabitCounterRepository {

    private companion object {
        val habitCounters = arrayListOf(
            HabitCounter(1, Periodicity.DAILY, 2),
            HabitCounter(1, Periodicity.WEEKLY, 2),
            HabitCounter(1, Periodicity.MONTHLY, 2),
            HabitCounter(1, Periodicity.YEARLY, 2)
        )
    }

    override suspend fun getLastDailyCounter() =
        habitCounters.findLast { it.periodicity == Periodicity.DAILY }!!

    override suspend fun getLastWeeklyCounter() =
        habitCounters.findLast { it.periodicity == Periodicity.WEEKLY }!!

    override suspend fun getLastMonthlyCounter() =
        habitCounters.findLast { it.periodicity == Periodicity.MONTHLY }!!

    override suspend fun getLastYearlyCounter() =
        habitCounters.findLast { it.periodicity == Periodicity.YEARLY }!!

    override suspend fun insert(habitCounter: HabitCounter) {
        habitCounters.add(habitCounter)
    }
}