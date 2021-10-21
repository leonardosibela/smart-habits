package com.sibela.smarthabits.presentation.view.activity

import androidx.test.core.app.launchActivity
import com.sibela.smarthabits.R
import com.sibela.smarthabits.presentation.utils.hasText
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.DAY
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.MONTH
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.SETTINGS
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.TITLE
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.WEEK
import com.sibela.smarthabits.presentation.view.activity.MainActivityTestRobotsString.Companion.YEAR
import com.sibela.smarthabits.presentation.view.fragment.BaseTestRobotAct

class MainActivityTestRobotsString {
    companion object {
        const val TITLE = "Smart Habits"
        const val DAY = "Day"
        const val WEEK = "Week"
        const val MONTH = "Month"
        const val YEAR = "Year"
        const val SETTINGS = "Settings"
    }
}

class MainActivityTestRobotsArrange {

    fun openActivity() {
        launchActivity<MainActivity>()
    }
}

class MainActivityTestRobotsAct : BaseTestRobotAct()

class MainActivityTestRobotsAssert {

    fun viewsHaveCorrectText() {
        R.id.toolbar_title.hasText(TITLE)
        R.id.dailyHabitsFragment.hasText(DAY)
        R.id.weeklyHabitsFragment.hasText(WEEK)
        R.id.monthlyHabitsFragment.hasText(MONTH)
        R.id.yearlyHabitsFragment.hasText(YEAR)
        R.id.settingsFragment.hasText(SETTINGS)
    }
}