package com.hikarisource.smarthabits.presentation.view.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.domain.model.Habit
import com.hikarisource.smarthabits.domain.model.Periodicity
import com.hikarisource.smarthabits.presentation.features.settings.view.fragment.EditHabitFragment
import com.hikarisource.smarthabits.presentation.utils.*
import com.hikarisource.smarthabits.presentation.view.fragment.EditHabitFragmentString.Companion.EDIT_HABIT_BUTTON_TEXT
import com.hikarisource.smarthabits.presentation.view.fragment.EditHabitFragmentString.Companion.HABIT
import com.hikarisource.smarthabits.presentation.view.fragment.EditHabitFragmentString.Companion.HABIT_DESCRIPTION
import com.hikarisource.smarthabits.presentation.view.fragment.EditHabitFragmentString.Companion.HABIT_INPUT_HINT
import com.hikarisource.smarthabits.presentation.view.fragment.EditHabitFragmentString.Companion.HABIT_KEY

class EditHabitFragmentString {
    companion object {
        const val HABIT_KEY = "habit"
        val HABIT = Habit(1, "Description", Periodicity.DAILY)
        const val HABIT_DESCRIPTION = "Caminhar por 30 minutps"
        const val EMPTY_TEXT = ""
        const val HABIT_INPUT_HINT = "Description"
        const val EDIT_HABIT_BUTTON_TEXT = "Edit habit"
    }
}

object EditHabitFragmentNavController {
    lateinit var navController: TestNavHostController
}

class EditHabitFragmentTestRobotArrange {

    fun openFragment() {
        EditHabitFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<EditHabitFragment>(
                themeResId = R.style.Theme_SmartHabits,
                fragmentArgs = bundleOf(HABIT_KEY to HABIT)
            )

        fragmentScenario.onFragment { fragment ->
            with(EditHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.editHabitFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class EditHabitFragmentTestRobotAct : BaseTestRobotAct() {

    fun typeHabitDescription() {
        R.id.description_input.typeText(HABIT_DESCRIPTION)
    }

    fun clickOnEditHabit() {
        R.id.edit_habit_button.click()
    }
}

class EditHabitFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.description_input.isDisplayed()
        R.id.edit_habit_button.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.description_input.isVisible()
        R.id.edit_habit_button.isVisible()
    }

    fun editHabitButtonHasCorrectText() {
        R.id.edit_habit_button.hasText(EDIT_HABIT_BUTTON_TEXT)
    }

    fun habitDescriptionHasCorrectText() {
        R.id.description_input.hasText(HABIT.description)
    }

    fun habitDescriptionHasCorrectHint() {
        R.id.description_input.hasHint(HABIT_INPUT_HINT)
    }

    fun editHabitIsEnabled() {
        R.id.edit_habit_button.isEnabled()
    }
}