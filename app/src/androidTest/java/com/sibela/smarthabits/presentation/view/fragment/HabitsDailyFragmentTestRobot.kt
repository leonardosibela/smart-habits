package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.DAILY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.DAILY_HABITS_ERROR
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.SECOND_HABIT_DESCRIPTION

class HabitsDailyFragmentString {

    companion object {
        const val DAILY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val DAILY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
    }
}

object HabitsDailyFragmentNavController {
    lateinit var navController: TestNavHostController
}

class HabitsDailyFragmentTestRobotArrange {

    fun openFragment() {
        HabitsDailyFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<DailyHabitsSettingsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.dailyHabitListFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class HabitsDailyFragmentTestRobotAct : BaseTestRobotAct()

class HabitsDailyFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.habits_recycler.isDisplayed()
        R.id.add_habit_fab.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.habits_recycler.isVisible()
        R.id.add_habit_fab.isVisible()
    }

    fun viewsAreGone() {
        R.id.habits_list_error.isGone()
        R.id.habits_empty_list_message.isGone()
    }

    fun viewsHaveCorrectText() {
        R.id.habits_list_error.hasText(DAILY_HABITS_ERROR)
        R.id.habits_empty_list_message.hasText(DAILY_HABITS_EMPTY_LIST_MESSAGE)

        R.id.habits_recycler.onItem(0) {
            childHasText(R.id.habit_description, FIRST_HABIT_DESCRIPTION)
        }

        R.id.habits_recycler.onItem(1) {
            childHasText(R.id.habit_description, SECOND_HABIT_DESCRIPTION)
        }
    }

    fun addHabitIsEnabled() {
        R.id.add_habit_fab.isEnabled()
    }
}