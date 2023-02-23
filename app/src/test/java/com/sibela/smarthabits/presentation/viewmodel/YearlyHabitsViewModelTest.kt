package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishYearlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyHabitsUseCase
import com.sibela.smarthabits.presentation.viewmodel.YearlyHabitsViewModel.Companion.YEARLY_HABIT_KEY
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class YearlyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishYearlyHabitUseCase: FinishYearlyHabitUseCase

    private lateinit var viewModel: YearlyHabitsViewModel

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
            YearlyHabitsViewModel(
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