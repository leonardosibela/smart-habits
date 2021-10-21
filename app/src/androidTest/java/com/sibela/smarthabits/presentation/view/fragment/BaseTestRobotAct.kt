package com.sibela.smarthabits.presentation.view.fragment

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

open class BaseTestRobotAct {

    fun clickDeviceBackButton() = Espresso.pressBack()

    fun waitFor(delay: Long) {
        onView(isRoot()).perform(wait(delay))
    }

    private fun wait(delay: Long) = object : ViewAction {
        override fun getConstraints(): Matcher<View> = isRoot()
        override fun getDescription(): String = "wait for $delay milliseconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(delay)
        }
    }
}