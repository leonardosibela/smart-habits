package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.features.settings.view.fragment.MonthlyHabitsSettingsFragment
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.MONTHLY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.MONTHLY_HABITS_ERROR
import com.sibela.smarthabits.presentation.view.fragment.HabitsMonthlyFragmentString.Companion.SECOND_HABIT_DESCRIPTION

class HabitsMonthlyFragmentString {

    companion object {
        const val MONTHLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val MONTHLY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
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
            launchFragmentInContainer<MonthlyHabitsSettingsFragment>(themeResId = R.style.Theme_SmartHabits)

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

        R.id.monthly_habits_recycler.onItem(0) {
            childHasText(R.id.habit_description, FIRST_HABIT_DESCRIPTION)
        }

        R.id.monthly_habits_recycler.onItem(1) {
            childHasText(R.id.habit_description, SECOND_HABIT_DESCRIPTION)
        }
    }

    fun addHabitIsEnabled() {
        R.id.add_monthly_habit_fab.isEnabled()
    }
}