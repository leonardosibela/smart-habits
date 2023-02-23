package com.sibela.smarthabits.extension

import org.junit.Assert
import org.junit.Test
import java.util.Calendar
import java.util.GregorianCalendar

internal class CalendarExtensionsKtTest {

    @Test
    fun `GIVEN Calendar day of the week is SUNDAY WHEN isFirstDayOfWeek CALLED MUST return true `() {
        // GIVEN
        val sundayCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.FEBRUARY)
            set(Calendar.DAY_OF_MONTH, 19)
        }

        // WHEN
        val isFirstDayOfWeek = sundayCalendar.isFirstDayOfWeek()

        // THEN
        Assert.assertTrue(isFirstDayOfWeek)
    }

    @Test
    fun `GIVEN Calendar day of the week is not SUNDAY WHEN isFirstDayOfWeek CALLED MUST return false `() {
        // GIVEN
        val mondayCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.FEBRUARY)
            set(Calendar.DAY_OF_MONTH, 20)
        }

        // WHEN
        val isFirstDayOfWeek = mondayCalendar.isFirstDayOfWeek()

        // THEN
        Assert.assertFalse(isFirstDayOfWeek)
    }

    @Test
    fun `GIVEN Calendar day of the month is 1 WHEN isFirstDayOfMonth CALLED MUST return true `() {
        // GIVEN
        val firstDayOfMonthCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.FEBRUARY)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        // WHEN
        val isFirstDayOfMonth = firstDayOfMonthCalendar.isFirstDayOfMonth()

        // THEN
        Assert.assertTrue(isFirstDayOfMonth)
    }

    @Test
    fun `GIVEN Calendar day of the month is not 1 WHEN isFirstDayOfMonth CALLED MUST return false `() {
        // GIVEN
        val secondDayOfMonthCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.FEBRUARY)
            set(Calendar.DAY_OF_MONTH, 2)
        }

        // WHEN
        val isFirstDayOfMonth = secondDayOfMonthCalendar.isFirstDayOfMonth()

        // THEN
        Assert.assertFalse(isFirstDayOfMonth)
    }

    @Test
    fun `GIVEN Calendar day of the year is 1 WHEN isFirstDayOfYear CALLED MUST return false `() {
        // GIVEN
        val firstDayOfYearCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 1)
        }

        // WHEN
        val isFirstDayOfYear = firstDayOfYearCalendar.isFirstDayOfYear()

        // THEN
        Assert.assertTrue(isFirstDayOfYear)
    }

    @Test
    fun `GIVEN Calendar day of the year is not 1 WHEN isFirstDayOfYear CALLED MUST return false `() {
        // GIVEN
        val secondDayOfTheYearCalendar: Calendar = GregorianCalendar().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 2)
        }

        // WHEN
        val isFirstDayOfYear = secondDayOfTheYearCalendar.isFirstDayOfYear()

        // THEN
        Assert.assertFalse(isFirstDayOfYear)
    }
}