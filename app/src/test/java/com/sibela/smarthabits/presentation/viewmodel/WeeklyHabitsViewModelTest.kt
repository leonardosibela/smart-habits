package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.WeeklyHabit
import com.sibela.smarthabits.domain.usecase.FinishWeeklyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentWeeklyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_WEEKLY_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*

class WeeklyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getCurrentWeeklyHabitsUseCase: GetCurrentWeeklyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishWeeklyHabitUseCase: FinishWeeklyHabitUseCase

    @RelaxedMockK
    private lateinit var habitsObserver: Observer<PeriodicHabitResult<WeeklyHabit>>

    @InjectMockKs
    private lateinit var weeklyHabitsViewModel: WeeklyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        weeklyHabitsViewModel.habits.observeForever(habitsObserver)
    }

    @After
    fun tearDown() {
        weeklyHabitsViewModel.habits.removeObserver(habitsObserver)
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns throwable.toError()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            weeklyHabitsViewModel.habits.value!!::class
        )
        weeklyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Error(throwable)) }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_WEEKLY_HABIT, SECOND_WEEKLY_HABIT)
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedList.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            weeklyHabitsViewModel.habits.value!!::class
        )
        weeklyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Success(expectedList)) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<WeeklyHabit>()
        coEvery { getCurrentWeeklyHabitsUseCase() } returns expectedHabits.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            weeklyHabitsViewModel.habits.value!!::class
        )
        weeklyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentWeeklyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.EmptyList) }
    }

    @Test
    fun finishHabit() {
        val weeklyHabit = FIRST_WEEKLY_HABIT
        coJustRun { finishWeeklyHabitUseCase(any()) }
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            weeklyHabitsViewModel.habits.value!!::class
        )
        weeklyHabitsViewModel.finishHabit(weeklyHabit)
        coVerify(exactly = 1) { finishWeeklyHabitUseCase.invoke(weeklyHabit) }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Loading) }
    }
}