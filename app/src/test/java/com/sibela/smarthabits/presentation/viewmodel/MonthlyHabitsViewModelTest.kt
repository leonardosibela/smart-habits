package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.usecase.FinishMonthlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentMonthlyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
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

class MonthlyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getCurrentMonthlyHabitsUseCase: GetCurrentMonthlyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishMonthlyHabitUseCase: FinishMonthlyHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: MonthlyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.Success(expectedList), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<MonthlyHabit>()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun finishHabit() {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coJustRun { finishMonthlyHabitUseCase(any()) }

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(monthlyHabit)

        coVerify(exactly = 1) { finishMonthlyHabitUseCase.invoke(monthlyHabit) }
        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)
    }
}