package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.not

@IdRes
fun Int.isVisible() {
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
}

@IdRes
fun Int.isInvisible() {
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
}

@IdRes
fun Int.isGone() {
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.GONE)))
}

@IdRes
fun Int.isDisplayed() {
    onView(withId(this)).check(matches(ViewMatchers.isDisplayed()))
}

@IdRes
fun Int.isEnabled() {
    onView(withId(this)).check(matches(ViewMatchers.isEnabled()))
}

@IdRes
fun Int.isDisabled() {
    onView(withId(this)).check(matches(not(ViewMatchers.isEnabled())))
}

@IdRes
fun Int.isClickable() {
    onView(withId(this)).check(matches(ViewMatchers.isClickable()))
}