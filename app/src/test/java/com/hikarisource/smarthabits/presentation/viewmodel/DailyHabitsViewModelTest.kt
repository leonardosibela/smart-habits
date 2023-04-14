package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.DailyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.DailyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_DAILY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DailyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentDailyHabitsUseCase: GetCurrentHabitsUseCase<DailyHabit>

    @RelaxedMockK
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
                finishDailyHabitUseCase,
                DispatcherHandlerUnconfined
            )
        )
    }

    @Test
    fun `fetchHabits result Error`() {
        val throwable = Throwable()
        coEvery { getCurrentDailyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Error
        }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Success(expectedList)
        }
    }

    @Test
    fun `fetchHabits result EmptyList`() {
        val expectedHabits = listOf<DailyHabit>()
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[DAILY_HABITS_KEY] = PeriodicHabitResult.EmptyList
        }
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