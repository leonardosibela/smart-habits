package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.DAILY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.HabitsDailyFragmentString.Companion.DAILY_HABITS_ERROR

class HabitsDailyFragmentString {

    companion object {
        const val DAILY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val DAILY_HABITS_ERROR = "Internal error"
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
            launchFragmentInContainer<HabitsDailyFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.dailyHabitListFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class HabitsDailyFragmentTestRobotAct : BaseTestRobotAction()

class HabitsDailyFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.daily_habits_recycler.isDisplayed()
        R.id.add_daily_habit_fab.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.daily_habits_recycler.isVisible()
        R.id.add_daily_habit_fab.isVisible()
    }

    fun viewsAreGone() {
        R.id.daily_habits_error.isGone()
        R.id.daily_habits_empty_list_message.isGone()
    }

    fun viewsHaveCorrectText() {
        R.id.daily_habits_error.hasText(DAILY_HABITS_ERROR)
        R.id.daily_habits_empty_list_message.hasText(DAILY_HABITS_EMPTY_LIST_MESSAGE)
    }

    fun addHabitIsEnabled() {
        R.id.add_daily_habit_fab.isEnabled()
    }
}