package com.hikarisource.smarthabits.presentation.view.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.hikarisource.smarthabits.R
import com.hikarisource.smarthabits.presentation.features.settings.view.fragment.SettingsFragment
import com.hikarisource.smarthabits.presentation.utils.*
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_DAILY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_MONTHLY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_WEEKLY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.CLICK_LIST_YEARLY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_DAILY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_MONTHLY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_WEEKLY_HABITS
import com.hikarisource.smarthabits.presentation.view.fragment.SettingsFragmentString.Companion.LIST_YEARLY_HABITS

class SettingsFragmentString {

    companion object {
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
            with(SettingsFragmentNavController) {
                navController.setGraph(R.navigation.main_nav_graph)
                navController.setCurrentDestination(R.id.settingsFragment)
                Navigation.setViewNavController(fragment.requireView(), navController)
            }
        }
    }
}

class SettingsFragmentTestRobotAct : BaseTestRobotAct() {

    fun clickOnResetDailyHabits() {
        R.id.recycler_view.onItem(0) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnResetWeeklyHabits() {
        R.id.recycler_view.onItem(1) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnResetMonthlyHabits() {
        R.id.recycler_view.onItem(2) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnResetYearlyHabits() {
        R.id.recycler_view.onItem(3) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnListDailyHabits() {
        R.id.recycler_view.onItem(4) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnListWeeklyHabits() {
        R.id.recycler_view.onItem(5) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnListMonthlyHabits() {
        R.id.recycler_view.onItem(6) {
            clickAt(android.R.id.title)
        }
    }

    fun clickOnListYearlyHabits() {
        R.id.recycler_view.onItem(7) {
            clickAt(android.R.id.title)
        }
    }
}

class SettingsFragmentTestRobotAssert {

    fun viewsHaveCorrectText() {
        R.id.recycler_view.onItem(0) {
            childHasText(android.R.id.title, LIST_DAILY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_DAILY_HABITS)
        }
        R.id.recycler_view.onItem(1) {
            childHasText(android.R.id.title, LIST_WEEKLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_WEEKLY_HABITS)
        }
        R.id.recycler_view.onItem(2) {
            childHasText(android.R.id.title, LIST_MONTHLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_MONTHLY_HABITS)
        }
        R.id.recycler_view.onItem(3) {
            childHasText(android.R.id.title, LIST_YEARLY_HABITS)
            childHasText(android.R.id.summary, CLICK_LIST_YEARLY_HABITS)
        }
    }
}