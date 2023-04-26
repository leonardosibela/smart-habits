package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.YearlyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.YearlyHabitListViewModel
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_YEARLY_HABIT
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
class YearlyHabitListViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    private val unconfinedTestDispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentHabitsUseCase<YearlyHabit>

    @RelaxedMockK
    private lateinit var finishYearlyHabitUseCase: FinishHabitUseCase<YearlyHabit>

    private lateinit var viewModel: YearlyHabitListViewModel

    companion object {
        private const val YEARLY_HABIT_KEY: String = "YEARLY_HABITS_KEY"
    }

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(YEARLY_HABIT_KEY, PeriodicHabitResult.Loading)
        } returns MutableStateFlow(PeriodicHabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = spyk(
            YearlyHabitListViewModel(
                savedStateHandle,
                getCurrentYearlyHabitsUseCase,
                finishYearlyHabitUseCase,
                DispatcherHandlerCustom(unconfinedTestDispatcher)
            )
        )
    }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error twice WHEN fetchHabits called THEN must set PeriodicHabitResult_Error`() =
        runTest(unconfinedTestDispatcher) {
            coEvery { getCurrentYearlyHabitsUseCase() } returns Throwable().toError()

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Loading
                getCurrentYearlyHabitsUseCase()
                getCurrentYearlyHabitsUseCase()
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Error
            }
        }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error once and then a success on the second attempt WHEN fetchHabits called THEN must set PeriodicHabitResult_Success`() =
        runTest(unconfinedTestDispatcher) {
            val expectedList = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
            coEvery { getCurrentYearlyHabitsUseCase() } returns Throwable().toError() andThen expectedList.toSuccess()
            viewModel.isFirstFetch = true

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Loading
                getCurrentYearlyHabitsUseCase()
                getCurrentYearlyHabitsUseCase()
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Success(expectedList)
            }
        }

    @Test
    fun `GIVEN getCurrentWeeklyHabits returns an error once and then an empty list on the second attempt WHEN fetchHabits called THEN must set PeriodicHabitResult_EmptyList`() =
        runTest(unconfinedTestDispatcher) {
            coEvery { getCurrentYearlyHabitsUseCase() } returns Throwable().toError() andThen emptyList<YearlyHabit>().toSuccess()
            viewModel.isFirstFetch = true

            Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

            viewModel.fetchHabits()

            advanceUntilIdle()

            coVerifyOrder {
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Loading
                getCurrentYearlyHabitsUseCase()
                getCurrentYearlyHabitsUseCase()
                savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.EmptyList
            }
        }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Success(expectedList)
        }
    }

    @Test
    fun `fetchHabits result EmptyList`() {
        val expectedHabits = listOf<YearlyHabit>()
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verifyOrder {
            savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Loading
            savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.EmptyList
        }
    }

    @Test
    fun finishHabit() {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coEvery { getCurrentYearlyHabitsUseCase.invoke() } returns listOf<YearlyHabit>().toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(yearlyHabit)

        coVerify(exactly = 1) { finishYearlyHabitUseCase.invoke(yearlyHabit) }
        verify { viewModel.fetchHabits() }
    }
}
