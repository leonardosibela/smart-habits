package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers

@IdRes
fun Int.click() {
    onView(withId(this)).perform(ViewActions.click())
}

@IdRes
fun Int.scrollInScrollView() {
    onView(withId(this)).perform(ViewActions.scrollTo())
}

@IdRes
fun Int.typeText(text: String) {
    onView(withId(this)).perform(ViewActions.typeText(text))
}

@IdRes
fun Int.clickOnSpinner(position: Int) {
    onView(withId(this)).perform(ViewActions.click())
    onData(Matchers.anything()).atPosition(position).perform(ViewActions.click())
}

@IdRes
fun Int.openLinkWithText(text: String) {
    onView(withId(this)).perform(ViewActions.openLinkWithText(text))
}
