package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.repository.HabitRepository
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SaveHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: HabitRepository

    @InjectMockKs
    private lateinit var saveHabitUseCase: SaveHabitUseCase

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runBlocking {
        val habit = TestData.FIRST_HABIT_DAILY
        coJustRun { repository.save(habit) }
        saveHabitUseCase.invoke(habit)
        coVerify(exactly = 1) { saveHabitUseCase.invoke(habit) }
    }
}