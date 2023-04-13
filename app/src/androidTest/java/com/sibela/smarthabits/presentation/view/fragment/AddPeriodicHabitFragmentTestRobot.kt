package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.features.settings.view.fragment.AddPeriodicHabitFragment
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.AddPeriodicHabitFragmentString.Companion.ADD_HABIT_BUTTON_TEXT
import com.sibela.smarthabits.presentation.view.fragment.AddPeriodicHabitFragmentString.Companion.EMPTY_TEXT
import com.sibela.smarthabits.presentation.view.fragment.AddPeriodicHabitFragmentString.Companion.HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.AddPeriodicHabitFragmentString.Companion.HABIT_INPUT_HINT

class AddPeriodicHabitFragmentString {
    companion object {
        const val HABIT_DESCRIPTION = "Caminhar por 30 minutps"
        const val EMPTY_TEXT = ""
        const val HABIT_INPUT_HINT = "Description"
        const val ADD_HABIT_BUTTON_TEXT = "Add habit"
    }
}

object AddPeriodicHabitFragmentNavController {
    lateinit var navController: TestNavHostController
}

class AddPeriodicHabitFragmentTestRobotArrange {

    fun openFragment() {
        AddPeriodicHabitFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<AddPeriodicHabitFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.addPeriodicHabitFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class AddPeriodicHabitFragmentTestRobotAct : BaseTestRobotAct() {

    fun typeHabitDescription() {
        R.id.description_input.typeText(HABIT_DESCRIPTION)
    }

    fun clickOnAddHabit() {
        R.id.add_habit_button.click()
    }
}

class AddPeriodicHabitFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.description_input.isDisplayed()
        R.id.add_habit_button.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.description_input.isVisible()
        R.id.add_habit_button.isVisible()
    }

    fun editHabitButtonHasCorrectText() {
        R.id.add_habit_button.hasText(ADD_HABIT_BUTTON_TEXT)
    }

    fun habitDescriptionIsEmpty() {
        R.id.description_input.hasText(EMPTY_TEXT)
    }

    fun habitDescriptionHasCorrectHint() {
        R.id.description_input.hasHint(HABIT_INPUT_HINT)
    }

    fun addHabitIsEnabled() {
        R.id.add_habit_button.isEnabled()
    }
}