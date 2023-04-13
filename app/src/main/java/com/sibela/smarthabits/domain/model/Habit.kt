package com.sibela.smarthabits.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Habit(
    var id: Int = 0,
    var description: String,
    val periodicity: Periodicity,
) : Parcelable