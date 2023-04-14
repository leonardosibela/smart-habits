package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

class ParentChildAssertImpl(@IdRes private val parent: Int) : ParentChildAssert {

    override fun childHasText(@IdRes childId: Int, text: String) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.withText(text))
        )
    }

    override fun childHasContentDescription(@IdRes childId: Int, text: String) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.withContentDescription(text))
        )
    }

    override fun childHasLinks(@IdRes childId: Int) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.hasLinks())
        )
    }

    override fun childIsEnabled(@IdRes childId: Int) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.isEnabled())
        )
    }

    override fun childIsVisible(@IdRes childId: Int) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
        )
    }

    override fun childIsInvisible(@IdRes childId: Int) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE))
        )
    }

    override fun childIsGone(@IdRes childId: Int) {
        getViewInteraction(childId).check(
            ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE))
        )
    }

    private fun getViewInteraction(childId: Int) = Espresso.onView(
        Matchers.allOf(
            ViewMatchers.withId(childId),
            ViewMatchers.isDescendantOfA(ViewMatchers.withId(parent))
        )
    )
}