package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WeeklyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentHabitsUseCase<WeeklyHabit>

    @RelaxedMockK
    private lateinit var finishWeeklyHabitUseCase: FinishHabitUseCase<WeeklyHabit>

    private lateinit var viewModel: WeeklyHabitListViewModel

    companion object {
        private const val WEEKLY_HABIT_KEY: String = "WEEKLY_HABIT_KEY"
    }

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(WEEKLY_HABIT_KEY, PeriodicHabitResult.Loading)
        } returns MutableStateFlow(PeriodicHabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = spyk(
            WeeklyHabitListViewModel(
                savedStateHandle,
                getCurrentWeeklyHabitsUseCase,
                finishWeeklyHabitUseCase
            )
        )
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify { savedStateHandle[WEEKLY_HABIT_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify { savedStateHandle[WEEKLY_HABIT_KEY] = PeriodicHabitResult.Success(expectedList) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<WeeklyHabit>()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify { savedStateHandle[WEEKLY_HABIT_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun finishHabit() {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coEvery { getCurrentWeeklyHabitsUseCase.invoke() } returns listOf<WeeklyHabit>().toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(weeklyHabit)

        coVerify(exactly = 1) { finishWeeklyHabitUseCase.invoke(weeklyHabit) }
        verify { viewModel.fetchHabits() }
    }
}