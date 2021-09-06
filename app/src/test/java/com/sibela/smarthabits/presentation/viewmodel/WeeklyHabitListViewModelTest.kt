package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.Habit
import com.sibela.smarthabits.domain.usecase.DeleteHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetHabitsThatAreWeeklyUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData
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

class WeeklyHabitListViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getHabitsThatAreWeeklyUseCase: GetHabitsThatAreWeeklyUseCase

    @RelaxedMockK
    private lateinit var deleteHabitUseCase: DeleteHabitUseCase

    @RelaxedMockK
    private lateinit var habitObserver: Observer<HabitResult>

    @InjectMockKs
    private lateinit var weeklyHabitListViewModel: WeeklyHabitListViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        weeklyHabitListViewModel.habits.observeForever(habitObserver)
    }

    @After
    fun tearDown() {
        weeklyHabitListViewModel.habits.removeObserver(habitObserver)
    }

    @Test
    fun `fetchHabits result error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getHabitsThatAreWeeklyUseCase() } returns throwable.toError()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        weeklyHabitListViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Error(throwable)) }
    }

    @Test
    fun `fetchHabits result empty`() = runBlocking {
        val expectedHabits = listOf<Habit>()
        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        weeklyHabitListViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.EmptyList) }
    }

    @Test
    fun `fetchHabits result success`() {
        val expectedHabits = listOf(TestData.FIRST_HABIT, TestData.SECOND_HABIT)
        coEvery { getHabitsThatAreWeeklyUseCase() } returns expectedHabits.toSuccess()
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Loading) }
        weeklyHabitListViewModel.fetchHabits()
        coVerify(exactly = 1) { getHabitsThatAreWeeklyUseCase.invoke() }
        verify(exactly = 1) { habitObserver.onChanged(HabitResult.Success(expectedHabits)) }
    }

    @Test
    fun deleteHabit() {
        coJustRun { deleteHabitUseCase(TestData.FIRST_HABIT) }
        weeklyHabitListViewModel.deleteHabit(TestData.FIRST_HABIT)
        coVerify(exactly = 1) { deleteHabitUseCase.invoke(TestData.FIRST_HABIT) }
    }
}