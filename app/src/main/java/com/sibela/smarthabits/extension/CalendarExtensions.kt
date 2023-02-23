package com.sibela.smarthabits.extension

import java.util.Calendar

fun Calendar.isFirstDayOfWeek() = this.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
fun Calendar.isFirstDayOfMonth() = this.get(Calendar.DAY_OF_MONTH) == 1
fun Calendar.isFirstDayOfYear() = this.get(Calendar.DAY_OF_YEAR) == 1

fun Calendar.getNextDayAtMidnight() = apply {
    set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH) + 1)
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}