package com.sibela.smarthabits.data.local

import com.sibela.smarthabits.util.TestData.DAILY_PERIODICITY
import com.sibela.smarthabits.util.TestData.DAILY_PERIODICITY_AS_STRING
import com.sibela.smarthabits.util.TestData.MONTHLY_PERIODICITY
import com.sibela.smarthabits.util.TestData.MONTHLY_PERIODICITY_AS_STRING
import com.sibela.smarthabits.util.TestData.WEEKLY_PERIODICITY
import com.sibela.smarthabits.util.TestData.WEEKLY_PERIODICITY_AS_STRING
import com.sibela.smarthabits.util.TestData.YEARLY_PERIODICITY
import com.sibela.smarthabits.util.TestData.YEARLY_PERIODICITY_AS_STRING
import org.junit.Assert
import org.junit.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun toDailyPeriodicity() {
        val dailyPeriodicity = converters.toPeriodicity(DAILY_PERIODICITY_AS_STRING)
        Assert.assertEquals(DAILY_PERIODICITY, dailyPeriodicity)
    }

    @Test
    fun fromDailyPeriodicity() {
        val dailyPeriodicityAsString = converters.fromPeriodicity(DAILY_PERIODICITY)
        Assert.assertEquals(DAILY_PERIODICITY_AS_STRING, dailyPeriodicityAsString)
    }

    @Test
    fun toWeeklyPeriodicity() {
        val weeklyPeriodicity = converters.toPeriodicity(WEEKLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(WEEKLY_PERIODICITY, weeklyPeriodicity)
    }

    @Test
    fun fromWeeklyPeriodicity() {
        val weeklyPeriodicityAsString = converters.fromPeriodicity(WEEKLY_PERIODICITY)
        Assert.assertEquals(WEEKLY_PERIODICITY_AS_STRING, weeklyPeriodicityAsString)
    }

    @Test
    fun toMonthlyPeriodicity() {
        val monthlyPeriodicity = converters.toPeriodicity(MONTHLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(MONTHLY_PERIODICITY, monthlyPeriodicity)
    }

    @Test
    fun fromMonthlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.fromPeriodicity(MONTHLY_PERIODICITY)
        Assert.assertEquals(MONTHLY_PERIODICITY_AS_STRING, monthlyPeriodicityAsString)
    }

    @Test
    fun toYearlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.toPeriodicity(YEARLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(YEARLY_PERIODICITY, monthlyPeriodicityAsString)
    }

    @Test
    fun fromYearlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.fromPeriodicity(YEARLY_PERIODICITY)
        Assert.assertEquals(YEARLY_PERIODICITY_AS_STRING, monthlyPeriodicityAsString)
    }
}