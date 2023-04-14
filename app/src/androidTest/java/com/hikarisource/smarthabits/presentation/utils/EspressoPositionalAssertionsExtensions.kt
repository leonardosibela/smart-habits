package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.matcher.ViewMatchers

@IdRes
infix fun Int.isLeftOf(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isCompletelyLeftOf(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isRightOf(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isCompletelyRightOf(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isAbove(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isCompletelyAbove(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isBelow(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isCompletelyBelow(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isLeftAlignWith(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isLeftAlignedWith(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isRightAlignWith(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isRightAlignedWith(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isBottomAlignWith(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isBottomAlignedWith(ViewMatchers.withId(id)))
}

@IdRes
infix fun Int.isTopAlignWith(@IdRes id: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .check(PositionAssertions.isTopAlignedWith(ViewMatchers.withId(id)))
}

