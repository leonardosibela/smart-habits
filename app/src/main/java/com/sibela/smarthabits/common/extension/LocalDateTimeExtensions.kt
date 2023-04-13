package com.sibela.smarthabits.common.extension

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun LocalDateTime.isFirstDayOfWeek() = dayOfWeek == DayOfWeek.SUNDAY

fun LocalDateTime.isFirstDayOfMonth() = dayOfMonth == 1

fun LocalDateTime.isFirstDayOfYear() = dayOfYear == 1

fun LocalDateTime.getNextDayAtMidnight(): LocalDateTime = plusDays(1)
    .withHour(0)
    .withMinute(0)
    .withSecond(0)
    .withNano(0)

infix fun LocalDateTime.hasYearAheadOf(localDateTime: LocalDateTime) = year > localDateTime.year

infix fun LocalDateTime.hasMonthAheadOf(localDateTime: LocalDateTime) =
    this hasYearAheadOf localDateTime ||
            ChronoUnit.MONTHS.between(localDateTime, this) > 0

infix fun LocalDateTime.hasWeekAheadOf(localDateTime: LocalDateTime) =
    this hasMonthAheadOf localDateTime ||
            ChronoUnit.WEEKS.between(localDateTime, this) > 0

infix fun LocalDateTime.hasDayAheadOf(localDateTime: LocalDateTime) =
    ChronoUnit.DAYS.between(localDateTime, this) > 0