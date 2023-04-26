package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.HabitCounter
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.repository.DataStoreRepository
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository

class PrePopulateDatabaseUseCase(
    private val dataStoreRepository: DataStoreRepository,
    private val habitCounterRepository: HabitCounterRepository
) {

    private companion object {
        const val PRE_POPULATED_DATABASE_KEY = "PRE_POPULATED_DATABASE_KEY"
    }

    @Suppress("MagicNumber")
    suspend operator fun invoke() {
        val alreadyPrePopulated = dataStoreRepository.readBoolean(PRE_POPULATED_DATABASE_KEY)
        if (alreadyPrePopulated.not()) {
            dataStoreRepository.saveBoolean(PRE_POPULATED_DATABASE_KEY, true)
            habitCounterRepository.insert(HabitCounter(1, Periodicity.DAILY, 1))
            habitCounterRepository.insert(HabitCounter(2, Periodicity.WEEKLY, 1))
            habitCounterRepository.insert(HabitCounter(3, Periodicity.MONTHLY, 1))
            habitCounterRepository.insert(HabitCounter(4, Periodicity.YEARLY, 1))
        }
    }
}
