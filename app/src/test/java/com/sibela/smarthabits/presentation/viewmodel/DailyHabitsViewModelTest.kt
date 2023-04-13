package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.sibela.smarthabits.presentation.features.list.viewmodel.DailyHabitListViewModel
import com.sibela.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
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

class DailyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<DailyHabit>

    private lateinit var finishDailyHabitUseCase: FinishHabitUseCase<DailyHabit>

    private lateinit var viewModel: DailyHabitListViewModel

    companion object {
        private const val DAILY_HABITS_KEY: String = "DAILY_HABITS_KEY"
    }

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(DAILY_HABITS_KEY, PeriodicHabitResult.Loading)
        } returns MutableStateFlow(PeriodicHabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = spyk(
            DailyHabitListViewModel(
                savedStateHandle,
                getCurrentDailyHabitsUseCase,
                finishDailyHabitUseCase
            )
        )
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentDailyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify { savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify { savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Success(expectedList) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<DailyHabit>()
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify { savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun finishHabit() {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { finishDailyHabitUseCase(any()) }

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(dailyHabit)
        coVerify(exactly = 1) { finishDailyHabitUseCase.invoke(dailyHabit) }
        verify { viewModel.fetchHabits() }
    }
}