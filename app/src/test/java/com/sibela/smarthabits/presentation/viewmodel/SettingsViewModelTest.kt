package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var resetDailyHabitsUseCase: ResetDailyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase

    @InjectMockKs
    private lateinit var settingsViewModel: SettingsViewModel

    @Before
    fun setUp() {
        initMockKAnnotations()
    }

    @Test
    fun resetDailyHabits() {
        coJustRun { resetDailyHabitsUseCase() }
        settingsViewModel.resetDailyHabits()
        coVerify(exactly = 1) { resetDailyHabitsUseCase() }
    }

    @Test
    fun resetWeeklyHabits() {
        coJustRun { resetWeeklyHabitsUseCase() }
        settingsViewModel.resetWeeklyHabits()
        coVerify(exactly = 1) { resetWeeklyHabitsUseCase() }
    }

    @Test
    fun resetMonthlyHabits() {
        coJustRun { resetMonthlyHabitsUseCase() }
        settingsViewModel.resetMonthlyHabits()
        coVerify(exactly = 1) { resetMonthlyHabitsUseCase() }
    }

    @Test
    fun resetYearlyHabits() {
        coJustRun { resetYearlyHabitsUseCase() }
        settingsViewModel.resetYearlyHabits()
        coVerify(exactly = 1) { resetYearlyHabitsUseCase() }
    }
}