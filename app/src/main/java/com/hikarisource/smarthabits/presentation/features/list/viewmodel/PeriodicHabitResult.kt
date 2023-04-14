package com.hikarisource.smarthabits.presentation.features.list.viewmodel

import android.os.Parcelable
import com.hikarisource.smarthabits.domain.model.PeriodicHabit
import kotlinx.parcelize.Parcelize

sealed class PeriodicHabitResult<out T : PeriodicHabit> : Parcelable {
    @Parcelize
    object Loading : PeriodicHabitResult<Nothing>()

    @Parcelize
    object EmptyList : PeriodicHabitResult<Nothing>()

    @Parcelize
    object Error : PeriodicHabitResult<Nothing>()

    @Parcelize
    data class Success<T : PeriodicHabit>(val data: List<T>) : PeriodicHabitResult<T>()
}