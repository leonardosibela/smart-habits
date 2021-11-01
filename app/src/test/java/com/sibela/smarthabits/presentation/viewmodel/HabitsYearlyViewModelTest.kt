package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreYearlyUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HabitsYearlyViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getHabitsThatAreYearlyUseCase: GetHabitsThatAreYearlyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: HabitsYearlyViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getHabitsThatAreYearlyUseCase() } returns throwable.toError()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        Assert.assertEquals(HabitResult.Error(throwable), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result empty`() = runBlocking {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        Assert.assertEquals(HabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(TestData.FIRST_HABIT_DAILY, TestData.SECOND_HABIT_DAILY)
        coEvery { getHabitsThatAreYearlyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreYearlyUseCase.invoke() }
        Assert.assertEquals(HabitResult.Success(expectedHabits), viewModel.habits.value)
    }

    @Test
    fun deleteHabit() {
        coJustRun { deleteHabitUseCase(TestData.FIRST_HABIT_DAILY) }
        viewModel.deleteHabit(TestData.FIRST_HABIT_DAILY)
        coVerify(exactly = 1) { deleteHabitUseCase.invoke(TestData.FIRST_HABIT_DAILY) }
    }
}