package com.sibela.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import com.sibela.smarthabits.presentation.viewmodel.HabitsWeeklyViewModel.Companion.HABITS_WEEKLY_KEY
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_WEEKLY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_WEEKLY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HabitsWeeklyViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    private lateinit var viewModel: HabitsWeeklyViewModel

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(
                HABITS_WEEKLY_KEY, HabitResult.Loading
            )
        } returns MutableStateFlow(HabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = HabitsWeeklyViewModel(
            savedStateHandle,
            getHabitsThatAreWeeklyUseCase,
            deleteHabitUseCase
        )
    }

    @Test
    fun `fetchHabits result error`() {
        val throwable = Throwable()
        coEvery { getHabitsThatAreWeeklyUseCase() } returns throwable.toError()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify {
            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.Error(throwable)
        }
    }

    @Test
    fun `fetchHabits result empty`() {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify {
            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.EmptyList
        }
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(FIRST_HABIT_WEEKLY, SECOND_HABIT_WEEKLY)
        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify {
            savedStateHandle[HABITS_WEEKLY_KEY] = HabitResult.Success(expectedHabits)
        }
    }

    @Test
    fun deleteHabit() {
        val habitToDelete = FIRST_HABIT_WEEKLY
        val initialHabits = arrayListOf(habitToDelete, SECOND_HABIT_WEEKLY)
        val expectedHabitResult = initialHabits.let {
            it.remove(habitToDelete)
            HabitResult.Success(it)
        }

        coEvery { getHabitsThatAreWeeklyUseCase.invoke() } returns initialHabits.toSuccess()
        viewModel.fetchHabits()
        coJustRun { deleteHabitUseCase(habitToDelete) }

        viewModel.deleteHabit(habitToDelete)

        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
        verify {
            savedStateHandle[HABITS_WEEKLY_KEY] = expectedHabitResult
        }
    }
}