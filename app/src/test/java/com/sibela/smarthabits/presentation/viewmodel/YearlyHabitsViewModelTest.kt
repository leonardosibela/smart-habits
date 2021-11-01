package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishYearlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class YearlyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishYearlyHabitUseCase: FinishYearlyHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: YearlyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentYearlyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.Success(expectedList), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<YearlyHabit>()
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun finishHabit() {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coJustRun { finishYearlyHabitUseCase(any()) }

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(yearlyHabit)

        coVerify(exactly = 1) { finishYearlyHabitUseCase.invoke(yearlyHabit) }
        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)
    }
}