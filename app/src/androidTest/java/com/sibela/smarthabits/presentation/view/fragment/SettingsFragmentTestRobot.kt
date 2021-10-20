package com.sibela.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.*
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_DAILY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_MONTHLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_WEEKLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_YEARLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_RESET_DAILY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_RESET_MONTHLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_RESET_WEEKLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_RESET_YEARLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_DAILY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_MONTHLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_WEEKLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_YEARLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.RESET_DAILY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.RESET_MONTHLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.RESET_WEEKLY_HABITS
import com.sibela.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.RESET_YEARLY_HABITS

class SettingsFragmentString {

    companion object {
        const val RESET_DAILY_HABITS = "Reset daily habits"
        const val RESET_WEEKLY_HABITS = "Reset weekly habits"
        const val RESET_MONTHLY_HABITS = "Reset monthly habits"
        const val RESET_YEARLY_HABITS = "Reset yearly habits"

        const val CLICK_RESET_DAILY_HABITS = "Click here to reset daily habits"
        const val CLICK_RESET_WEEKLY_HABITS = "Click here to reset weekly habits"
        const val CLICK_RESET_MONTHLY_HABITS = "Click here to reset monthly habits"
        const val CLICK_RESET_YEARLY_HABITS = "Click here to reset yearly habits"

        const val LIST_DAILY_HABITS = "List daily habits"
        const val LIST_WEEKLY_HABITS = "List weekly habits"
        const val LIST_MONTHLY_HABITS = "List monthly habits"
        const val LIST_YEARLY_HABITS = "List yearly habits"

        const val CLICK_LIST_DAILY_HABITS = "Click here to list daily habits"
        const val CLICK_LIST_WEEKLY_HABITS = "Click here to list weekly habits"
        const val CLICK_LIST_MONTHLY_HABITS = "Click here to list monthly habits"
        const val CLICK_LIST_YEARLY_HABITS = "Click here to list yearly habits"
    }
}

object SettingsFragmentNavController {
    lateinit var navController: TestNavHostController
}

class SettingsFragmentTestRobotArrange {

    fun openFragment() {
        SettingsFragmentNavController.navController =
            TestNavHostController(ApplicationProvider.getApplicationContext())

        val fragmentScenario =
            launchFragmentInContainer<SettingsFragment>(themeResId = R.style.Theme_SmartHabits)

        fragmentScenario.onFragment { fragment ->
            with(AddPeriodicHabitFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.settingsFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class SettingsFragmentTestRobotAct : BaseTestRobotAction()

class SettingsFragmentTestRobotAssert {

    fun viewsHaveCorrectText() {
        R.id.recycler_view.onItem(0) {
            childHasText(android.R.id.title, RESET_DAILY_HABITS)
            childHasText(android.R.id.summary, CLICK_RESET_DAILY_HABITS)
        }
        R.id.recycler_view.onItem(1) {
            childHasText(android.R.id.title, RESET_WEEKLY_HABITS)
            childHasText(android.R.id.summary, CLICK_RESET_WEEKLY_HABITS)
        }
        R.id.recycler_view.onItem(2) {
            childHasText(android.R.id.title, RESET_MONTHLY_HABITS)
            childHasText(android.R.id.summary, CLICK_RESET_MONTHLY_HABITS)
        }
        R.id.recycler_view.onItem(3) {
            childHasText(android.R.id.title, RESET_YEARLY_HABITS)
            childHasText(android.R.id.summary, CLICK_RESET_YEARLY_HABITS)
        }
        R.id.recycler_view.onItem(4) {
            childHasText(android.R.id.title, LIST_DAILY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_DAILY_HABITS)
        }
        R.id.recycler_view.onItem(5) {
            childHasText(android.R.id.title, LIST_WEEKLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_WEEKLY_HABITS)
        }
        R.id.recycler_view.onItem(6) {
            childHasText(android.R.id.title, LIST_MONTHLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_MONTHLY_HABITS)
        }
        R.id.recycler_view.onItem(7) {
            childHasText(android.R.id.title, LIST_YEARLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_YEARLY_HABITS)
        }
    }
}