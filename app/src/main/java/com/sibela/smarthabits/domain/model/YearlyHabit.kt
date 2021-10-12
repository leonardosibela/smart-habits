package com.sibela.smarthabits.domain.model

data class YearlyHabit(
    override var id: Int = 0,
    override val description: String,
    val completed: Boolean,
    var period: Int
) : PeriodicHabit