package com.hikarisource.smarthabits.domain.usecase

import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.repository.PeriodicHabitRepository
import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class FinishYearlyHabitUseCaseTest {

    @RelaxedMockK
    private lateinit var yearlyHabitRepository: PeriodicHabitRepository<YearlyHabit>

    @InjectMockKs
    private lateinit var finishYearlyHabitUseCase: FinishHabitUseCase<YearlyHabit>

    init {
        initMockKAnnotations()
    }

    @Test
    fun invoke() = runTest {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coJustRun { yearlyHabitRepository.remove(yearlyHabit) }

        finishYearlyHabitUseCase(yearlyHabit)

        coVerify(exactly = 1) { yearlyHabitRepository.remove(yearlyHabit) }
    }
}
