package com.hikarisource.smarthabits.domain.model

data class HabitCounter(
    var id: Int = 0,
    val periodicity: Periodicity,
    var period: Int
)
