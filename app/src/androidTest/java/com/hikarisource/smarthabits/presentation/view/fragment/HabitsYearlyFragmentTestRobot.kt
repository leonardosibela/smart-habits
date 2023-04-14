package com.hikarisource.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.presentation.features.settings.view.fragment.YearlyHabitsSettingsFragment
import com.hikarisource.smarthabits.presentation.utils.*
import com.hikarisource.smarthabits.presentation.view.fragment.HabitsYearlyFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.hikarisource.smarthabits.presentation.view.fragment.HabitsYearlyFragmentString.Companion.SECOND_HABIT_DESCRIPTION
import com.hikarisource.smarthabits.presentation.view.fragment.HabitsYearlyFragmentString.Companion.YEARLY_HABITS_EMPTY_LIST_MESSAGE
import com.hikarisource.smarthabits.presentation.view.fragment.HabitsYearlyFragmentString.Companion.YEARLY_HABITS_ERROR

class HabitsYearlyFragmentString {

    companion object {
        const val YEARLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val YEARLY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
    }
}

object HabitsYearlyFragmentNavController {
    lateinit var navController: TestNavHostController
}

class HabitsYearlyFragmentTestRobotArrange {

    fun openFragment() {
        HabitsYearlyFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<YearlyHabitsSettingsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.monthlyHabitListFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class HabitsYearlyFragmentTestRobotAct : BaseTestRobotAct()

class HabitsYearlyFragmentTestRobotAssert {

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