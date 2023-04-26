package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.containsString

@IdRes
fun Int.hasText(text: String) {
    onView(withId(this)).check(matches(withText(text)))
}

@IdRes
fun Int.hasHint(text: String) {
    onView(withId(this)).check(matches(ViewMatchers.withHint(text)))
}

@IdRes
fun Int.spinnerHasText(text: String) {
    onView(withId(this)).check(matches(withSpinnerText(containsString(text))))
}

@IdRes
fun Int.hasLinks() {
    onView(withId(this)).check(matches(ViewMatchers.hasLinks()))
}

@IdRes
fun Int.hasContentDescription(text: String) {
    onView(withId(this)).check(matches(withContentDescription(text)))
}

fun onParent(@IdRes parent: Int, scope: ParentChildAssert.() -> Unit) {
    scope.invoke(ParentChildAssertImpl(parent))
}

fun toastWithTextIsDisplayed(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
}
