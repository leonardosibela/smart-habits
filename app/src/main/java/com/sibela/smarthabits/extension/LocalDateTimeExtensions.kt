package com.sibela.smarthabits.extension

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun LocalDateTime.isFirstDayOfWeek() = dayOfWeek == DayOfWeek.SUNDAY

fun LocalDateTime.isFirstDayOfMonth() = dayOfMonth == 1

fun LocalDateTime.isFirstDayOfYear() = dayOfYear == 1

fun LocalDateTime.getNextDayAtMidnight(): LocalDateTime = LocalDateTime.now().plusSeconds(10)

infix fun LocalDateTime.hasYearAheadOf(localDateTime: LocalDateTime) = year > localDateTime.year

infix fun LocalDateTime.hasMonthAheadOf(localDateTime: LocalDateTime) =
    this hasYearAheadOf localDateTime ||
            ChronoUnit.MONTHS.between(localDateTime, this) > 0

infix fun LocalDateTime.hasWeekAheadOf(localDateTime: LocalDateTime) =
    this hasMonthAheadOf  localDateTime ||
    ChronoUnit.WEEKS.between(localDateTime, this) > 0

infix fun LocalDateTime.hasDayAheadOf(localDateTime: LocalDateTime) =
    ChronoUnit.DAYS.between(localDateTime, this) > 0