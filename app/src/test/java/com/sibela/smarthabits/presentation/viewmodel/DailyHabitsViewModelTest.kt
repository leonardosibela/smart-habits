package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.DailyHabit
import com.sibela.smarthabits.domain.usecase.FinishDailyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentDailyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_DAILY_HABIT
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

class DailyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getCurrentDailyHabitsUseCase: GetCurrentDailyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishDailyHabitUseCase: FinishDailyHabitUseCase

    @RelaxedMockK
    private lateinit var habitsObserver: Observer<PeriodicHabitResult<DailyHabit>>

    @InjectMockKs
    private lateinit var dailyHabitsViewModel: DailyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        dailyHabitsViewModel.habits.observeForever(habitsObserver)
    }

    @After
    fun tearDown() {
        dailyHabitsViewModel.habits.removeObserver(habitsObserver)
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentDailyHabitsUseCase() } returns throwable.toError()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            dailyHabitsViewModel.habits.value!!::class
        )
        dailyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Error(throwable)) }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_DAILY_HABIT, SECOND_DAILY_HABIT)
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedList.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            dailyHabitsViewModel.habits.value!!::class
        )
        dailyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Success(expectedList)) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<DailyHabit>()
        coEvery { getCurrentDailyHabitsUseCase() } returns expectedHabits.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            dailyHabitsViewModel.habits.value!!::class
        )
        dailyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentDailyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.EmptyList) }
    }

    @Test
    fun finishHabit() {
        val dailyHabit = FIRST_DAILY_HABIT
        coJustRun { finishDailyHabitUseCase(any()) }
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            dailyHabitsViewModel.habits.value!!::class
        )
        dailyHabitsViewModel.finishHabit(dailyHabit)
        coVerify(exactly = 1) { finishDailyHabitUseCase.invoke(dailyHabit) }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Loading) }
    }
}