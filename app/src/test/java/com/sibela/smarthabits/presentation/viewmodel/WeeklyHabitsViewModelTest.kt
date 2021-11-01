package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishWeeklyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentWeeklyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
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

class WeeklyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentWeeklyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishWeeklyHabitUseCase: FinishWeeklyHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: WeeklyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.Success(expectedList), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<WeeklyHabit>()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun finishHabit() {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coJustRun { finishWeeklyHabitUseCase(any()) }

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(weeklyHabit)

        coVerify(exactly = 1) { finishWeeklyHabitUseCase.invoke(weeklyHabit) }
        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)
    }
}