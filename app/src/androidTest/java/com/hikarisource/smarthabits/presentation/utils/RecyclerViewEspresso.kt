package com.hikarisource.smarthabits.presentation.utils

import androidx.annotation.IdRes

interface RecyclerViewEspresso : ParentChildAssert, ParentChildAction

interface ParentChildEspresso

interface ParentChildAssert : ParentChildEspresso {
    fun childHasText(@IdRes childId: Int, text: String)
    fun childHasContentDescription(@IdRes childId: Int, text: String)
    fun childHasLinks(@IdRes childId: Int)
    fun childIsEnabled(@IdRes childId: Int)
    fun childIsVisible(@IdRes childId: Int)
    fun childIsInvisible(@IdRes childId: Int)
    fun childIsGone(@IdRes childId: Int)
}

interface ParentChildAction : ParentChildEspresso {
    fun clickAt(@IdRes viewId: Int)
}