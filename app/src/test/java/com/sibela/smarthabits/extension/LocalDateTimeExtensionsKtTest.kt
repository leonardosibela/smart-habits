package com.sibela.smarthabits.extension

import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class LocalDateTimeExtensionsKtTest {

    @Test
    fun `GIVEN firstDate has days ahead of secondDate WHEN hasDayAheadOf called THEN must return true`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 7, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        assertTrue(firstDate hasDayAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate does not have days ahead of secondDate WHEN hasDayAheadOf called THEN must return false`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 7, 0, 0)
        assertFalse(firstDate hasDayAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate has days ahead of secondDate WHEN hasWeekAheadOf called THEN must return true`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 20, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        assertTrue(firstDate hasWeekAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate does not have days ahead of secondDate WHEN hasWeekAheadOf called THEN must return false`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 20, 0, 0)
        assertFalse(firstDate hasWeekAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate has days ahead of secondDate WHEN hasMonthAheadOf called THEN must return true`() {
        val firstDate = LocalDateTime.of(2023, Month.APRIL, 2, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        assertTrue(firstDate hasMonthAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate does not have days ahead of secondDate WHEN hasMonthAheadOf called THEN must return false`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.APRIL, 2, 0, 0)
        assertFalse(firstDate hasMonthAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate has years ahead of secondDate WHEN hasYearAheadOf called THEN must return true`() {
        val firstDate = LocalDateTime.of(2024, Month.MARCH, 2, 0, 0)
        val secondDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        assertTrue(firstDate hasYearAheadOf secondDate)
    }

    @Test
    fun `GIVEN firstDate does not have years ahead of secondDate WHEN hasYearAheadOf called THEN must return false`() {
        val firstDate = LocalDateTime.of(2023, Month.MARCH, 2, 0, 0)
        val secondDate = LocalDateTime.of(2024, Month.MARCH, 2, 0, 0)
        assertFalse(firstDate hasYearAheadOf secondDate)
    }

    @Test
    fun `GIVEN LocalDateTime day of the week is SUNDAY WHEN isFirstDayOfWeek CALLED MUST return true`() {
        // GIVEN
        val sunday = LocalDateTime.of(2023, Month.MARCH, 5, 0, 0)

        // WHEN
        val isFirstDayOfWeek = sunday.isFirstDayOfWeek()

        // THEN
        Assert.assertTrue(isFirstDayOfWeek)
    }

    @Test
    fun `GIVEN LocalDateTime day of the week is not SUNDAY WHEN isFirstDayOfWeek CALLED MUST return false`() {
        // GIVEN
        val monday = LocalDateTime.of(2023, Month.MARCH, 6, 0, 0)

        // WHEN
        val isFirstDayOfWeek = monday.isFirstDayOfWeek()

        // THEN
        Assert.assertFalse(isFirstDayOfWeek)
    }

    @Test
    fun `GIVEN LocalDateTime day of the month is 1 WHEN isFirstDayOfMonth CALLED MUST return true`() {
        // GIVEN
        val firstDayOfMonth = LocalDateTime.of(2023, Month.FEBRUARY, 1, 0, 0)

        // WHEN
        val isFirstDayOfMonth = firstDayOfMonth.isFirstDayOfMonth()

        // THEN
        Assert.assertTrue(isFirstDayOfMonth)
    }

    @Test
    fun `GIVEN LocalDateTime day of the month is not 1 WHEN isFirstDayOfMonth CALLED MUST return false`() {
        // GIVEN
        val secondDayOfMonth = LocalDateTime.of(2023, Month.FEBRUARY, 2, 0, 0)

        // WHEN
        val isFirstDayOfMonth = secondDayOfMonth.isFirstDayOfMonth()

        // THEN
        Assert.assertFalse(isFirstDayOfMonth)
    }

    @Test
    fun `GIVEN LocalDateTime day of the year is 1 WHEN isFirstDayOfYear CALLED MUST return false`() {
        // GIVEN
        val firstDayOfYear: LocalDateTime = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)

        // WHEN
        val isFirstDayOfYear = firstDayOfYear.isFirstDayOfYear()

        // THEN
        Assert.assertTrue(isFirstDayOfYear)
    }

    @Test
    fun `GIVEN LocalDateTime dayOf the year is not 1 WHEN isFirstDayOfYear CALLED MUST return false`() {
        // GIVEN
        val secondDayOfTheYear: LocalDateTime = LocalDateTime.of(2023, Month.JANUARY, 2, 0, 0)

        // WHEN
        val isFirstDayOfYear = secondDayOfTheYear.isFirstDayOfYear()

        // THEN
        Assert.assertFalse(isFirstDayOfYear)
    }

    @Test
    fun getNextDayAtMidnight() {
        val dateTime = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 30)
        val nextDayAtMidnight = LocalDateTime.of(2023, Month.JANUARY, 2, 0, 0, 0, 0)

        Assert.assertEquals(
            nextDayAtMidnight.toEpochSecond(ZoneOffset.UTC),
            dateTime.getNextDayAtMidnight().toEpochSecond(ZoneOffset.UTC)
        )
    }
}