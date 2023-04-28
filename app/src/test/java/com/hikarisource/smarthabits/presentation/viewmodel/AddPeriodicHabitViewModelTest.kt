package com.hikarisource.smarthabits.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.domain.usecase.AddHabitUseCase
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.AddPeriodicHabitViewModel
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.AddPeriodicHabitViewModel.Companion.DESCRIPTION_STATE_KEY
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.Disable
import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.Enable
import com.hikarisource.smarthabits.util.CoroutineTestRule
import com.hikarisource.smarthabits.util.TestData.BLANK_DESCRIPTION
import com.hikarisource.smarthabits.util.TestData.DAILY_PERIODICITY
import com.hikarisource.smarthabits.util.TestData.FIRST_DESCRIPTION
import com.hikarisource.smarthabits.util.initMockKAnnotations
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
@Suppress("MaxLineLength")
class AddPeriodicHabitViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var addHabitUseCase: AddHabitUseCase

    @RelaxedMockK
    private lateinit var savedStateHandle: SavedStateHandle

    @InjectMockKs
    private lateinit var addPeriodicHabitViewModel: AddPeriodicHabitViewModel

    @Before
    fun setUp() {
        initMockKAnnotations()
    }

    @Test
    fun `GIVEN description WHEN addHabit called THEN addHabitUseCase must be called`() {
        val description = FIRST_DESCRIPTION

        addPeriodicHabitViewModel.addHabit(description, DAILY_PERIODICITY)

        coVerify(exactly = 1) { addHabitUseCase.invoke(FIRST_DESCRIPTION, DAILY_PERIODICITY) }
    }

    @Test
    fun `GIVEN description is blank WHEN onDescriptionChanged called THEN descriptionErrorState must be EmptyError`() {
        val description = BLANK_DESCRIPTION

        addPeriodicHabitViewModel.onDescriptionChanged(description)

        verify { savedStateHandle[DESCRIPTION_STATE_KEY] = Disable }
    }

    @Test
    fun `GIVEN description is not blank WHEN onDescriptionChanged called THEN descriptionErrorState must be NoError`() {
        val description = FIRST_DESCRIPTION

        addPeriodicHabitViewModel.onDescriptionChanged(description)

        verify { savedStateHandle[DESCRIPTION_STATE_KEY] = Enable }
    }
}
