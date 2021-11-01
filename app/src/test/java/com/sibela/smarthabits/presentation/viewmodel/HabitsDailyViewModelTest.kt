package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreDailyUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY
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

class HabitsDailyViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var getHabitsThatAreDailyUseCase: GetHabitsThatAreDailyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    @InjectMockKs
    private lateinit var viewModel: HabitsDailyViewModel

    init {
        initMockKAnnotations()
    }

    @Test
    fun `fetchHabits result error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getHabitsThatAreDailyUseCase() } returns throwable.toError()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        Assert.assertEquals(HabitResult.Error(throwable), viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result empty`() = runBlocking {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreDailyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        Assert.assertEquals(HabitResult.EmptyList, viewModel.habits.value)
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        coEvery { getHabitsThatAreDailyUseCase() } returns expectedHabits.toSuccess()

        Assert.assertEquals(HabitResult.Loading, viewModel.habits.value)

        viewModel.fetchHabits()

        coVerify(exactly = 1) { getHabitsThatAreDailyUseCase.invoke() }
        Assert.assertEquals(HabitResult.Success(expectedHabits), viewModel.habits.value)
    }

    @Test
    fun deleteHabit() {
        coJustRun { deleteHabitUseCase(FIRST_HABIT_DAILY) }
        viewModel.deleteHabit(FIRST_HABIT_DAILY)
        coVerify(exactly = 1) { deleteHabitUseCase.invoke(FIRST_HABIT_DAILY) }
    }
}