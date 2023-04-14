package com.hikarisource.smarthabits.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class MonthlyHabit(
    override var id: Int = 0,
    override val description: String,
    val completed: Boolean,
    var period: Int
) : PeriodicHabit