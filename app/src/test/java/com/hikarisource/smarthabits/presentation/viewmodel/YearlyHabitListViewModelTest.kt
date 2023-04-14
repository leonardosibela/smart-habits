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

class YearlyHabitListViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentHabitsUseCase<YearlyHabit>

    @RelaxedMockK
    private lateinit var finishYearlyHabitUseCase: FinishHabitUseCase<YearlyHabit>

    private lateinit var viewModel: YearlyHabitListViewModel

    companion object {
        private const val YEARLY_HABIT_KEY: String = "YEARLY_HABIT_KEY"
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
                finishYearlyHabitUseCase
            )
        )
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentYearlyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify { savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify { savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.Success(expectedList) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<YearlyHabit>()
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify { savedStateHandle[YEARLY_HABIT_KEY] = PeriodicHabitResult.EmptyList }
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