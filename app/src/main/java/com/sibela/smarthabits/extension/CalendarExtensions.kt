package com.sibela.smarthabits.extension

import java.util.*

fun Calendar.getAtFirstTimeOfDay() = this.apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}