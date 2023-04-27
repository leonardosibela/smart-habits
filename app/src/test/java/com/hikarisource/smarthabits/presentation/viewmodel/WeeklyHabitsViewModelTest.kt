package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.WeeklyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.WeeklyHabitListViewModel
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@Suppress("MaxLineLength")
class WeeklyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentHabitsUseCase<WeeklyHabit>

    @RelaxedMockK
    private lateinit var finishWeeklyHabitUseCase: FinishHabitUseCase<WeeklyHabit>

    private lateinit var viewModel: WeeklyHabitListViewModel

    companion object {
        private const val WEEKLY_HABITS_KEY: String = "WEEKLY_HABITS_KEY"
    }

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(WEEKLY_HABITS_KEY, PeriodicHabitResult.Loading)
        } returns MutableStateFlow(PeriodicHabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = spyk(
            WeeklyHabitListViewModel(
                savedStateHandle,
                getCurrentWeeklyHabitsUseCase,
                finishWeeklyHabitUseCase,
                DispatcherHandlerCustom(unconfinedTestDispatcher)
            )
        )
    }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error twice WHEN fetchHabits called THEN must set PeriodicHabitResult_Error`() =
        runTest(unconfinedTestDispatcher) {
            coEvery { getCurrentWeeklyHabitsUseCase() } returns Throwable().toError()

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Loading
                getCurrentWeeklyHabitsUseCase()
                getCurrentWeeklyHabitsUseCase()
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Error
            }
        }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error once and then a success on the second attempt WHEN fetchHabits called THEN must set PeriodicHabitResult_Success`() =
        runTest(unconfinedTestDispatcher) {
            val expectedList = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
            coEvery {
                getCurrentWeeklyHabitsUseCase()
            } returns Throwable().toError() andThen expectedList.toSuccess()
            viewModel.isFirstFetch = true

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Loading
                getCurrentWeeklyHabitsUseCase()
                getCurrentWeeklyHabitsUseCase()
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Success(expectedList)
            }
        }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error once and then an empty list on the second attempt WHEN fetchHabits called THEN must set PeriodicHabitResult_EmptyList`() =
        runTest(unconfinedTestDispatcher) {
            coEvery {
                getCurrentWeeklyHabitsUseCase()
            } returns Throwable().toError() andThen emptyList<WeeklyHabit>().toSuccess()
            viewModel.isFirstFetch = true

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Loading
                getCurrentWeeklyHabitsUseCase()
                getCurrentWeeklyHabitsUseCase()
                savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.EmptyList
            }
        }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns Success with some list WHEN fetchHabits called THEN must set PeriodicHabitResult_Success with same list`() {
        val expectedList = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Success(expectedList)
        }
    }

    @Test
    fun `fetchHabits result EmptyList`() {
        val expectedHabits = listOf<WeeklyHabit>()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[WEEKLY_HABITS_KEY] = PeriodicHabitResult.EmptyList
        }
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
