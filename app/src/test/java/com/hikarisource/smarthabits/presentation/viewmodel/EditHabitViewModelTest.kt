package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.EditHabitUseCase
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EditHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EditHabitViewModel.Companion.DESCRIPTION_STATE_KEY
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.EmptyError
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.NoError
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
class EditHabitViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var editHabitUseCase: EditHabitUseCase

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @InjectMockKs
    private lateinit var editHabitViewModel: EditHabitViewModel

    @Before
    fun setUp() {
        initMockKAnnotations()
    }

    @Test
    fun editHabit() {
        val habit = FIRST_HABIT_DAILY
        val description = FIRST_DESCRIPTION

        coJustRun { editHabitUseCase(habit, description) }

        editHabitViewModel.editHabit(habit, description)
        coVerify(exactly = 1) { editHabitUseCase(habit, description) }
    }


    @Test
    fun `GIVEN description is blank WHEN editHabit called THEN editHabitUseCase must not be called`() {
        val description = TestData.BLANK_DESCRIPTION
        val habit = TestData.HABIT_DAILY

        editHabitViewModel.editHabit(habit, description)

        verify { savedStateHandle[DESCRIPTION_STATE_KEY] = EmptyError }
        coVerify(exactly = 0) { editHabitUseCase.invoke(habit, description) }
    }

    @Test
    fun `GIVEN description not blank WHEN editHabit called THEN editHabitUseCase must be called`() {
        val description = FIRST_DESCRIPTION
        val habit = TestData.HABIT_DAILY

        editHabitViewModel.editHabit(habit, description)

        coVerify(exactly = 1) { editHabitUseCase.invoke(habit, description) }
    }

    @Test
    fun `GIVEN description is blank WHEN onDescriptionChanged called THEN descriptionErrorState must be EmptyError`() {
        val description = TestData.BLANK_DESCRIPTION

        editHabitViewModel.onDescriptionChanged(description)

        verify { savedStateHandle[DESCRIPTION_STATE_KEY] = EmptyError }
    }

    @Test
    fun `GIVEN description is not blank WHEN onDescriptionChanged called THEN descriptionErrorState must be NoError`() {
        val description = FIRST_DESCRIPTION

        editHabitViewModel.onDescriptionChanged(description)

        verify { savedStateHandle[DESCRIPTION_STATE_KEY] = NoError }
    }
}