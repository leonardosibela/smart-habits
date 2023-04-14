package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.HabitCounter
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.domain.repository.DataStoreRepository
import com.hikarisource.smarthabits.domain.repository.HabitCounterRepository
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PrePopulateDatabaseUseCaseTest {

    private companion object {
        const val PRE_POPULATED_DATABASE_KEY = "PRE_POPULATED_DATABASE_KEY"
    }

    @RelaxedMockK
    private lateinit var dataStoreRepository: DataStoreRepository

    @RelaxedMockK
    private lateinit var habitCounterRepository: HabitCounterRepository

    @InjectMockKs
    private lateinit var prePopulateDatabaseUseCase: PrePopulateDatabaseUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun `invoke not pre populated`() = runBlocking {
        coEvery { dataStoreRepository.readBoolean(PRE_POPULATED_DATABASE_KEY) } returns false

        prePopulateDatabaseUseCase.invoke()

        coVerify(exactly = 1) { dataStoreRepository.saveBoolean(PRE_POPULATED_DATABASE_KEY, true) }
        coVerify(exactly = 1) {
            habitCounterRepository.insert(HabitCounter(1, Periodicity.DAILY, 1))
        }
        coVerify(exactly = 1) {
            habitCounterRepository.insert(HabitCounter(2, Periodicity.WEEKLY, 1))
        }
        coVerify(exactly = 1) {
            habitCounterRepository.insert(HabitCounter(3, Periodicity.MONTHLY, 1))
        }
        coVerify(exactly = 1) {
            habitCounterRepository.insert(HabitCounter(4, Periodicity.YEARLY, 1))
        }
    }

    @Test
    fun `invoke already pre populated`() = runBlocking {
        coEvery { dataStoreRepository.readBoolean(any()) } returns true

        prePopulateDatabaseUseCase.invoke()

        coVerify(exactly = 0) { dataStoreRepository.saveBoolean(any(), any()) }
        coVerify(exactly = 0) { habitCounterRepository.insert(any()) }
    }
}