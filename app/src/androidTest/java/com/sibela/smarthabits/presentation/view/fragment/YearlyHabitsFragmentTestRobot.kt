package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.features.list.view.fragment.YearlyHabitsFragment
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.YearlyHabitsFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.YearlyHabitsFragmentString.Companion.SECOND_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.YearlyHabitsFragmentString.Companion.YEARLY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.YearlyHabitsFragmentString.Companion.YEARLY_HABITS_ERROR

class YearlyHabitsFragmentString {

    companion object {
        const val YEARLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val YEARLY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
    }
}

object YearlyHabitsFragmentNavController {
    lateinit var navController: TestNavHostController
}

class YearlyHabitsFragmentTestRobotArrange {

    fun openFragment() {
        YearlyHabitsFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<YearlyHabitsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.yearlyHabitsFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class YearlyHabitsFragmentTestRobotAct : BaseTestRobotAct()

class YearlyHabitsFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.yearly_habits_recycler.isDisplayed()
        R.id.add_yearly_habit_fab.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.yearly_habits_recycler.isVisible()
        R.id.add_yearly_habit_fab.isVisible()
    }

    fun viewsAreGone() {
        R.id.yearly_habits_error.isGone()
        R.id.yearly_habits_empty_list_message.isGone()
    }

    fun viewsHaveCorrectText() {
        R.id.yearly_habits_error.hasText(YEARLY_HABITS_ERROR)
        R.id.yearly_habits_empty_list_message.hasText(YEARLY_HABITS_EMPTY_LIST_MESSAGE)

        R.id.yearly_habits_recycler.onItem(0) {
            childHasText(R.id.habit_description, FIRST_HABIT_DESCRIPTION)
        }

        R.id.yearly_habits_recycler.onItem(1) {
            childHasText(R.id.habit_description, SECOND_HABIT_DESCRIPTION)
        }
    }

    fun addHabitIsEnabled() {
        R.id.add_yearly_habit_fab.isEnabled()
    }
}