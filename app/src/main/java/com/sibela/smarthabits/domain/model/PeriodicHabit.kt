package com.sibela.smarthabits.domain.model

import android.os.Parcelable

interface PeriodicHabit : Parcelable {
    val id: Int
    val description: String
}