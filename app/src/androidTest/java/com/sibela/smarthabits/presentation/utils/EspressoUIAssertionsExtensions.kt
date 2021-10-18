package com.sibela.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import org.hamcrest.Matchers.containsString

@IdRes
fun Int.hasText(text: String) {
    onView(withId(this)).check(matches(ViewMatchers.withText(text)))
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
    onView(withId(this)).check(matches(ViewMatchers.withContentDescription(text)))
}

fun onParent(@IdRes parent: Int, scope: ParentChildAssert.() -> Unit) {
    scope.invoke(ParentChildAssertImpl(parent))
}