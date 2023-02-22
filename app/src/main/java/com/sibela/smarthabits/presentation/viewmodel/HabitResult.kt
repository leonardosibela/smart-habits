package com.sibela.smarthabits.presentation.viewmodel

import android.os.Parcelable
import com.sibela.smarthabits.domain.model.Habit
import kotlinx.parcelize.Parcelize

sealed class HabitResult : Parcelable {

    @Parcelize
    object Loading : HabitResult()

    @Parcelize
    object EmptyList : HabitResult()

    @Parcelize
    data class Success(val data: List<Habit>) : HabitResult()

    @Parcelize
    data class Error(val data: Throwable) : HabitResult()
}