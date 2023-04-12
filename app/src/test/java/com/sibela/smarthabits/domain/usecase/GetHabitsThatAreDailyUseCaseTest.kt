package com.sibela.smarthabits.domain.usecase

import com.sibela.smarthabits.domain.repository.HabitRepository
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetHabitsThatAreDailyUseCaseTest {

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @InjectMockKs
    private lateinit var getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val expectedHabits = listOf(TestData.FIRST_HABIT_DAILY, TestData.SECOND_HABIT_DAILY)
        coEvery { habitRepository.getAllHabitsThatAreDaily() } returns expectedHabits
        val result = getHabitsThatAreDailyUseCase.invoke()
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreDaily() }
        assertEquals(expectedHabits, result.value)
    }
}