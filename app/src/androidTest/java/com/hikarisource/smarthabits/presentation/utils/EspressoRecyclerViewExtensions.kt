package com.hikarisource.smarthabits.presentation.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

@IdRes
fun Int.checkItemsAmount(amount: Int) {
    Espresso.onView(ViewMatchers.withId(this)).check(RecyclerViewItemCountAssertion(amount))
}

@IdRes
inline fun Int.onItem(position: Int, scope: RecyclerViewEspresso.() -> Unit) {
    RecyclerViewEspressoImpl(this, position).scope()
}

@IdRes
fun <T : RecyclerView.ViewHolder> Int.clickOnItemAt(@IdRes viewId: Int, position: Int) {
    Espresso.onView(ViewMatchers.withId(this))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<T>(position, clickChildViewWithId(viewId))
        )
}

fun clickChildViewWithId(id: Int) = object : ViewAction {
    override fun getConstraints(): Matcher<View> = ViewMatchers.withId(id)

    override fun getDescription(): String = "click on view ith id: $id"

    override fun perform(uiController: UiController?, view: View?) {
        view?.findViewById<View>(id)?.performClick()
    }
}