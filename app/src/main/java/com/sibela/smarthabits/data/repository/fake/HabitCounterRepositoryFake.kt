package com.sibela.smarthabits.data.repository.fake

import com.sibela.smarthabits.domain.model.Periodicity
import com.sibela.smarthabits.domain.model.HabitCounter
import com.sibela.smarthabits.domain.repository.HabitCounterRepository

class HabitCounterRepositoryFake : HabitCounterRepository {

    override suspend fun getLastDailyCounter() = HabitCounter(0, Periodicity.DAILY, 1)
    override suspend fun getLastWeeklyCounter() = HabitCounter(0, Periodicity.WEEKLY, 1)
    override suspend fun getLastMonthlyCounter() = HabitCounter(0, Periodicity.MONTHLY, 1)
    override suspend fun getLastYearlyCounter() = HabitCounter(0, Periodicity.YEARLY, 1)

    override suspend fun insert(habitCounter: HabitCounter) {

    }
}