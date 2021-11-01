package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.MonthlyHabit
import com.sibela.smarthabits.domain.usecase.FinishMonthlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentMonthlyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_MONTHLY_HABIT
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

class MonthlyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getCurrentMonthlyHabitsUseCase: GetCurrentMonthlyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishMonthlyHabitUseCase: FinishMonthlyHabitUseCase

    @RelaxedMockK
    private lateinit var habitsObserver: Observer<PeriodicHabitResult<MonthlyHabit>>

    @InjectMockKs
    private lateinit var monthlyHabitsViewModel: MonthlyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        monthlyHabitsViewModel.habits.observeForever(habitsObserver)
    }

    @After
    fun tearDown() {
        monthlyHabitsViewModel.habits.removeObserver(habitsObserver)
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns throwable.toError()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            monthlyHabitsViewModel.habits.value!!::class
        )
        monthlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.EmptyList) }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_MONTHLY_HABIT, SECOND_MONTHLY_HABIT)
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedList.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            monthlyHabitsViewModel.habits.value!!::class
        )
        monthlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Success(expectedList)) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<MonthlyHabit>()
        coEvery { getCurrentMonthlyHabitsUseCase() } returns expectedHabits.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            monthlyHabitsViewModel.habits.value!!::class
        )
        monthlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentMonthlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.EmptyList) }
    }

    @Test
    fun finishHabit() {
        val monthlyHabit = FIRST_MONTHLY_HABIT
        coJustRun { finishMonthlyHabitUseCase(any()) }
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            monthlyHabitsViewModel.habits.value!!::class
        )
        monthlyHabitsViewModel.finishHabit(monthlyHabit)
        coVerify(exactly = 1) { finishMonthlyHabitUseCase.invoke(monthlyHabit) }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Loading) }
    }
}