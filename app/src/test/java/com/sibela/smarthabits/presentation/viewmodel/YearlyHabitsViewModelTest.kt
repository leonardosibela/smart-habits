package com.sibela.smarthabits.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sibela.smarthabits.domain.common.toError
import com.sibela.smarthabits.domain.common.toSuccess
import com.sibela.smarthabits.domain.model.YearlyHabit
import com.sibela.smarthabits.domain.usecase.FinishYearlyHabitUseCase
import com.sibela.smarthabits.domain.usecase.GetCurrentYearlyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_YEARLY_HABIT
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

class YearlyHabitsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getCurrentYearlyHabitsUseCase: GetCurrentYearlyHabitsUseCase

    @RelaxedMockK
    private lateinit var finishYearlyHabitUseCase: FinishYearlyHabitUseCase

    @RelaxedMockK
    private lateinit var habitsObserver: Observer<PeriodicHabitResult<YearlyHabit>>

    @InjectMockKs
    private lateinit var yearlyHabitsViewModel: YearlyHabitsViewModel

    init {
        initMockKAnnotations()
    }

    @Before
    fun setUp() {
        yearlyHabitsViewModel.habits.observeForever(habitsObserver)
    }

    @After
    fun tearDown() {
        yearlyHabitsViewModel.habits.removeObserver(habitsObserver)
    }

    @Test
    fun `fetchHabits result Error`() = runBlocking {
        val throwable = Throwable()
        coEvery { getCurrentYearlyHabitsUseCase() } returns throwable.toError()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            yearlyHabitsViewModel.habits.value!!::class
        )
        yearlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Error(throwable)) }
    }

    @Test
    fun `fetchHabits result Success`() {
        val expectedList = listOf(FIRST_YEARLY_HABIT, SECOND_YEARLY_HABIT)
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedList.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            yearlyHabitsViewModel.habits.value!!::class
        )
        yearlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Success(expectedList)) }
    }

    @Test
    fun `fetchHabits result EmptyList`() = runBlocking {
        val expectedHabits = listOf<YearlyHabit>()
        coEvery { getCurrentYearlyHabitsUseCase() } returns expectedHabits.toSuccess()
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            yearlyHabitsViewModel.habits.value!!::class
        )
        yearlyHabitsViewModel.fetchHabits()
        coVerify(exactly = 1) { getCurrentYearlyHabitsUseCase.invoke() }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.EmptyList) }
    }

    @Test
    fun finishHabit() {
        val yearlyHabit = FIRST_YEARLY_HABIT
        coJustRun { finishYearlyHabitUseCase(any()) }
        Assert.assertEquals(
            PeriodicHabitResult.Loading::class,
            yearlyHabitsViewModel.habits.value!!::class
        )
        yearlyHabitsViewModel.finishHabit(yearlyHabit)
        coVerify(exactly = 1) { finishYearlyHabitUseCase.invoke(yearlyHabit) }
        verify(exactly = 1) { habitsObserver.onChanged(PeriodicHabitResult.Loading) }
    }
}