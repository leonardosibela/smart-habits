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

class GetHabitsThatAreWeeklyUseCaseTest {

    @RelaxedMockK
    private lateinit var habitRepository: HabitRepository

    @InjectMockKs
    private lateinit var getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val expectedHabits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        coEvery { habitRepository.getAllHabitsThatAreWeekly() } returns expectedHabits
        val result = getHabitsThatAreWeeklyUseCase.invoke()
        coVerify(exactly = 1) { habitRepository.getAllHabitsThatAreWeekly() }
        assertEquals(expectedHabits, result.result)
    }
}