package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.DAILY_HABITS_EMPTY_LIST_MESSAGE
import com.sibela.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.DAILY_HABITS_ERROR
import com.sibela.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.sibela.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.SECOND_HABIT_DESCRIPTION

class DailyHabitsFragmentString {

    companion object {
        const val DAILY_HABITS_EMPTY_LIST_MESSAGE = "No habits added"
        const val DAILY_HABITS_ERROR = "Internal error"
        const val FIRST_HABIT_DESCRIPTION = "Read some pages of a book"
        const val SECOND_HABIT_DESCRIPTION = "Exercise for at last 30 min"
    }
}

object DailyHabitsFragmentNavController {
    lateinit var navController: TestNavHostController
}

class DailyHabitsFragmentTestRobotArrange {

    fun openFragment() {
        DailyHabitsFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<DailyHabitsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(DailyHabitsFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.dailyHabitsFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class DailyHabitsFragmentTestRobotAct : BaseTestRobotAct()

class DailyHabitsFragmentTestRobotAssert {

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

        R.id.daily_habits_recycler.onItem(0) {
            childHasText(R.id.habit_description, FIRST_HABIT_DESCRIPTION)
        }

        R.id.daily_habits_recycler.onItem(1) {
            childHasText(R.id.habit_description, SECOND_HABIT_DESCRIPTION)
        }
    }

    fun addHabitIsEnabled() {
        R.id.add_daily_habit_fab.isEnabled()
    }
}