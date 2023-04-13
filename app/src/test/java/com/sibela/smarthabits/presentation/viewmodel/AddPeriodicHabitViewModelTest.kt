package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.usecase.AddHabitUseCase
import com.sibela.smarthabits.presentation.features.settings.viewmodel.AddPeriodicHabitViewModel
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.DAILY_PERIODICITY
import com.sibela.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddPeriodicHabitViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var addHabitUseCase: AddHabitUseCase

    @InjectMockKs
    private lateinit var addPeriodicHabitViewModel: AddPeriodicHabitViewModel

    @Before
    fun setUp() {
        initMockKAnnotations()
    }

    @Test
    fun addHabit() {
        coJustRun { addHabitUseCase.invoke(FIRST_DESCRIPTION, DAILY_PERIODICITY) }
        addPeriodicHabitViewModel.addHabit(FIRST_DESCRIPTION, DAILY_PERIODICITY)
        coVerify(exactly = 1) { addHabitUseCase.invoke(FIRST_DESCRIPTION, DAILY_PERIODICITY) }
    }
}