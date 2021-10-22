package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.MonthlyHabitsFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.MonthlyHabitsFragmentString.Companion.MONTHLY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.MonthlyHabitsFragmentString.Companion.MONTHLY_HABITS_ERROR
import com.sibela.smarthabits.presentation.view.fragment.MonthlyHabitsFragmentString.Companion.SECOND_HABIT_DESCRIPTION

class MonthlyHabitsFragmentString {

    companion object {
        const val MONTHLY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val MONTHLY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
    }
}

object MonthlyHabitsFragmentNavController {
    lateinit var navController: TestNavHostController
}

class MonthlyHabitsFragmentTestRobotArrange {

    fun openFragment() {
        MonthlyHabitsFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<MonthlyHabitsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.monthlyHabitsFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class MonthlyHabitsFragmentTestRobotAct : BaseTestRobotAct()

class MonthlyHabitsFragmentTestRobotAssert {

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