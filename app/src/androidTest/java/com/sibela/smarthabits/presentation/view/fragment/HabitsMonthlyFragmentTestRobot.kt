package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.MONTHLY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.MONTHLY_HABITS_ERROR

class HabitsMonthlyFragmentString {

    companion object {
        const val MONTHLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val MONTHLY_HABITS_ERROR = "Internal error"
    }
}

object HabitsMonthlyFragmentNavController {
    lateinit var navController: TestNavHostController
}

class HabitsMonthlyFragmentTestRobotArrange {

    fun openFragment() {
        HabitsMonthlyFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<HabitsMonthlyFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(HabitsMonthlyFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.monthlyHabitListFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class HabitsMonthlyFragmentTestRobotAct : BaseTestRobotAct()

class HabitsMonthlyFragmentTestRobotAssert {

    fun viewsAreDisplayed() {
        R.id.monthly_habits_recycler.isDisplayed()
        R.id.add_monthly_habit_fab.isDisplayed()
    }

    fun viewsAreVisible() {
        R.id.monthly_habits_recycler.isVisible()
        R.id.add_monthly_habit_fab.isVisible()
    }

    fun viewsAreGone() {
        R.id.monthly_habits_error.isGone()
        R.id.monthly_habits_empty_list_message.isGone()
    }

    fun viewsHaveCorrectText() {
        R.id.monthly_habits_error.hasText(MONTHLY_HABITS_ERROR)
        R.id.monthly_habits_empty_list_message.hasText(MONTHLY_HABITS_EMPTY_LIST_MESSAGE)
    }

    fun addHabitIsEnabled() {
        R.id.add_monthly_habit_fab.isEnabled()
    }
}