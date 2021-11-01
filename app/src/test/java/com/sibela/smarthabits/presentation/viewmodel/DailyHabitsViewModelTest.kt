package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishDailyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentDailyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
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

class DailyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getCurrentDailyHabitsUseCase: GetCurrentDailyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishDailyHabitUseCase: FinishDailyHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: DailyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentDailyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.Success(expectedList), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<DailyHabit>()
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        Assert.assertEquals(PeriodicHabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun finishHabit() {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { finishDailyHabitUseCase(any()) }

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(dailyHabit)
        coVerify(exactly = 1) { finishDailyHabitUseCase.invoke(dailyHabit) }
        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)
    }
}