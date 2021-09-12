package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.domain.usecase.EditHabitUseCase
import com.sibela.smarthabits.util.CoroutineTestRule
import com.sibela.smarthabits.util.TestData.FIRST_HABIT
import com.sibela.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditHabitViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var editHabitUseCase: EditHabitUseCase

    @InjectMockKs
    private lateinit var editHabitViewModel: EditHabitViewModel

    @Before
    fun setUp() {
        initMockKAnnotations()
    }

    @Test
    fun editHabit() {
        val habit = FIRST_HABIT
        val description = "Description"
        coJustRun { editHabitUseCase(habit, description) }
        editHabitViewModel.editHabit(habit, description)
        coVerify(exactly = 1) { editHabitUseCase(habit, description) }
    }
}