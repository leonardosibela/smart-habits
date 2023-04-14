package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetHabitsThatAreYearlyUseCaseTest {

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @InjectMockKs
    private lateinit var getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val expectedHabits = listOf(TestData.FIRST_HABIT_DAILY, TestData.SECOND_HABIT_DAILY)
        coEvery { habitRepository.getAllHabitsThatAreYearly() } returns expectedHabits

        val result = getHabitsThatAreYearlyUseCase.invoke()

        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreYearly() }
        assertEquals(expectedHabits, result.value)
    }
}