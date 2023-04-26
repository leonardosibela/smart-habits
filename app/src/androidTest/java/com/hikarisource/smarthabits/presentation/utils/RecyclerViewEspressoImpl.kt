package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility

class RecyclerViewEspressoImpl(
    @IdRes private val recyclerViewId: Int,
    private val position: Int
) : RecyclerViewEspresso {

    override fun childHasText(@IdRes childId: Int, text: String) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

    override fun childHasContentDescription(@IdRes childId: Int, text: String) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(ViewMatchers.withContentDescription(text)))
    }

    override fun clickAt(@IdRes viewId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, viewId))
            .perform(ViewActions.click())
    }

    override fun childHasLinks(@IdRes childId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(ViewMatchers.hasLinks()))
    }

    override fun childIsEnabled(childId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    override fun childIsVisible(childId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    override fun childIsInvisible(childId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(
                ViewAssertions.matches(
                    withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)
                )
            )
    }

    override fun childIsGone(childId: Int) {
        onView(RecyclerViewMatcher(recyclerViewId).atPositionOnView(position, childId))
            .check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }
}
