package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.MonthlyHabit
import com.hikarisource.smarthabits.domain.usecase.FinishHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetCurrentHabitsUseCase
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.MonthlyHabitListViewModel
import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
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

class MonthlyHabitListViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getCurrentMonthlyHabitsUseCase: GetCurrentHabitsUseCase<MonthlyHabit>

    @RelaxedMockK
    private lateinit var finishMonthlyHabitUseCase: FinishHabitUseCase<MonthlyHabit>

    private lateinit var viewModel: MonthlyHabitListViewModel

    companion object {
        private const val MONTHLY_HABITS_KEY: String = "MONTHLY_HABITS_KEY"
    }

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(MONTHLY_HABITS_KEY, PeriodicHabitResult.Loading)
        } returns MutableStateFlow(PeriodicHabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = spyk(
            MonthlyHabitListViewModel(
                savedStateHandle,
                getCurrentMonthlyHabitsUseCase,
                finishMonthlyHabitUseCase
            )
        )
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns throwable.toError()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify { savedStateHandle[MONTHLY_HABITS_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedList.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify { savedStateHandle[MONTHLY_HABITS_KEY] = PeriodicHabitResult.Success(expectedList) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<MonthlyHabit>()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify { savedStateHandle[MONTHLY_HABITS_KEY] = PeriodicHabitResult.EmptyList }
    }

    @Test
    fun finishHabit() {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coEvery { getCurrentMonthlyHabitsUseCase.invoke() } returns listOf<MonthlyHabit>().toSuccess()

        Assert.assertEquals(PeriodicHabitResult.Loading, viewModel.habits.value)

        viewModel.finishHabit(monthlyHabit)

        coVerify(exactly = 1) { finishMonthlyHabitUseCase.invoke(monthlyHabit) }
        verify { viewModel.fetchHabits() }
    }
}