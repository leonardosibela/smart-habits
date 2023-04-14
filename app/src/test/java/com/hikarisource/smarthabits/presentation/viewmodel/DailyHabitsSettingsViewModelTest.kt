package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.DailyHabitsSettingsViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitResult
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitsSettingsViewModel.Companion.HABITS_KEY
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.hikarisource.smarthabits.util.TestData.SECOND_HABIT_DAILY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DailyHabitsSettingsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    private lateinit var viewModel: DailyHabitsSettingsViewModel

    init {
        initMockKAnnotations()
        mockInitialValueForHabitResult()
        initializeViewModel()
    }

    private fun mockInitialValueForHabitResult() {
        every {
            savedStateHandle.getStateFlow(HABITS_KEY, HabitResult.Loading)
        } returns MutableStateFlow(HabitResult.Loading)
    }

    private fun initializeViewModel() {
        viewModel = DailyHabitsSettingsViewModel(
            savedStateHandle,
            getHabitsThatAreDailyUseCase,
            deleteHabitUseCase,
            DispatcherHandlerUnconfined
        )
    }

    @Test
    fun `fetchHabits result error`() {
        val throwable = Throwable()
        coEvery { getHabitsThatAreDailyUseCase() } returns throwable.toError()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.Error(throwable) }
    }

    @Test
    fun `fetchHabits result empty`() {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreDailyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        coEvery { getHabitsThatAreDailyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.Success(expectedHabits) }
    }

    @Test
    fun deleteHabit() {
        val habitToDelete = FIRST_HABIT_DAILY
        val initialHabits = arrayListOf(habitToDelete, SECOND_HABIT_DAILY)
        val expectedHabitResult = initialHabits.let {
            it.remove(habitToDelete)
            HabitResult.Success(it)
        }

        coEvery { getHabitsThatAreDailyUseCase.invoke() } returns initialHabits.toSuccess()
        viewModel.fetchHabits()
        coJustRun { deleteHabitUseCase(habitToDelete) }

        viewModel.deleteHabit(habitToDelete)

        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
        verify { savedStateHandle[HABITS_KEY] = expectedHabitResult }
    }
}