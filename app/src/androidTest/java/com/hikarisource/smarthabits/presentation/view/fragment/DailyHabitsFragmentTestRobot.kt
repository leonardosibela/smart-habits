package com.hikarisource.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.presentation.features.list.view.fragment.DailyHabitsFragment
import com.hikarisource.smarthabits.presentation.utils.*
import com.hikarisource.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.DAILY_HABITS_EMPTY_LIST_MESSAGE
import com.hikarisource.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.DAILY_HABITS_ERROR
import com.hikarisource.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.FIRST_HABIT_DESCRIPTION
import com.hikarisource.smarthabits.presentation.view.fragment.DailyHabitsFragmentString.Companion.SECOND_HABIT_DESCRIPTION

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