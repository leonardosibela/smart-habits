package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.common.toError
import com.hikarisource.smarthabits.domain.common.toSuccess
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.usecase.DeleteHabitUseCase
import com.hikarisource.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitResult
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitsSettingsViewModel.Companion.HABITS_KEY
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.YearlyHabitsSettingsViewModel
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class YearlyHabitsSettingsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @RelaxedMockK
    private lateinit var getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    private lateinit var viewModel: YearlyHabitsSettingsViewModel

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
        viewModel = YearlyHabitsSettingsViewModel(
            savedStateHandle,
            getHabitsThatAreYearlyUseCase,
            deleteHabitUseCase,
            DispatcherHandlerCustom(UnconfinedTestDispatcher())
        )
    }

    @Test
    fun `fetchHabits result error`() {
        val throwable = Throwable()
        coEvery { getHabitsThatAreYearlyUseCase() } returns throwable.toError()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.Error(throwable) }
    }

    @Test
    fun `fetchHabits result empty`() {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.EmptyList }
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(TestData.FIRST_HABIT_DAILY, TestData.SECOND_HABIT_DAILY)
        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        verify { savedStateHandle[HABITS_KEY] = HabitResult.Success(expectedHabits) }
    }

    @Test
    fun deleteHabit() {
        val habitToDelete = TestData.FIRST_HABIT_DAILY
        val initialHabits = arrayListOf(habitToDelete, TestData.SECOND_HABIT_DAILY)
        val expectedHabitResult = initialHabits.let {
            it.remove(habitToDelete)
            HabitResult.Success(it)
        }

        coEvery { getHabitsThatAreYearlyUseCase.invoke() } returns initialHabits.toSuccess()
        viewModel.fetchHabits()
        coJustRun { deleteHabitUseCase(habitToDelete) }

        viewModel.deleteHabit(habitToDelete)

        coVerify(exactly = 1) { deleteHabitUseCase.invoke(habitToDelete) }
        verify { savedStateHandle[HABITS_KEY] = expectedHabitResult }
    }
}
