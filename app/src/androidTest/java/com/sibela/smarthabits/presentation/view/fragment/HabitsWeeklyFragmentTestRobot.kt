package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.HabitsWeeklyFragmentString.Companion.WEEKLY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.HabitsWeeklyFragmentString.Companion.WEEKLY_HABITS_ERROR

class HabitsWeeklyFragmentString {

    companion object {
        const val WEEKLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val WEEKLY_HABITS_ERROR = "Internal error"
    }
}

object HabitsWeeklyFragmentNavController {
    lateinit var navController: TestNavHostController
}

class HabitsWeeklyFragmentTestRobotArrange {

    fun openFragment() {
        AddPeriodicHabitFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<HabitsWeeklyFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.weeklyHabitListFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class HabitsWeeklyFragmentTestRobotAct : BaseTestRobotAction()

class HabitsWeeklyFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.weekly_habits_recycler.isDisplayed()
        R.id.add_weekly_habit_fab.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.weekly_habits_recycler.isVisible()
        R.id.add_weekly_habit_fab.isVisible()
    }

    fun viewsAreGone() {
        R.id.weekly_habits_error.isGone()
        R.id.weekly_habits_empty_list_message.isGone()
    }

    fun viewsHaveCorrectText() {
        R.id.weekly_habits_error.hasText(WEEKLY_HABITS_ERROR)
        R.id.weekly_habits_empty_list_message.hasText(WEEKLY_HABITS_EMPTY_LIST_MESSAGE)
    }

    fun addHabitIsEnabled() {
        R.id.add_weekly_habit_fab.isEnabled()
    }
}