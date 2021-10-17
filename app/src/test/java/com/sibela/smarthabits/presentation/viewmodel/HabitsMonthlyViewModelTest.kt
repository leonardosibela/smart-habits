package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreMonthlyUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.sibela.smarthabits.util.TestData.SECOND_HABIT_DAILY
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HabitsMonthlyViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getHabitsThatAreMonthlyUseCase: GetHabitsThatAreMonthlyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    @RelaxedMockK
    private lateinit var habitObserver: Observer<HabitResult>

    @InjectMockKs
    private lateinit var habitsMonthlyViewModel: HabitsMonthlyViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        habitsMonthlyViewModel.habits.observeForever(habitObserver)
    }

    @After
    fun tearDown() {
        habitsMonthlyViewModel.habits.removeObserver(habitObserver)
    }

    @Test
    fun `fetchHabits result error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getHabitsThatAreMonthlyUseCase() } returns throwable.toError()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        habitsMonthlyViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Error(throwable)) }
    }

    @Test
    fun `fetchHabits result empty`() = runBlocking {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreMonthlyUseCase() } returns expectedHabits.toSuccess()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        habitsMonthlyViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.EmptyList) }
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        coEvery { getHabitsThatAreMonthlyUseCase() } returns expectedHabits.toSuccess()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        habitsMonthlyViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreMonthlyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Success(expectedHabits)) }
    }

    @Test
    fun deleteHabit() {
        coJustRun { deleteHabitUseCase(FIRST_HABIT_DAILY) }
        habitsMonthlyViewModel.deleteHabit(FIRST_HABIT_DAILY)
        coVerify(exactly = 1) { deleteHabitUseCase.invoke(FIRST_HABIT_DAILY) }
    }
}