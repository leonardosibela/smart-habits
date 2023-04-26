package com.hikarisource.smarthabits.data.local

import com.hikarisource.smarthabits.util.TestData.DAILY_PERIODICITY_AS_STRING
import com.hikarisource.smarthabits.util.TestData.DAILY_PERIODICITY_ENTITY
import com.hikarisource.smarthabits.util.TestData.MONTHLY_PERIODICITY_AS_STRING
import com.hikarisource.smarthabits.util.TestData.MONTHLY_PERIODICITY_ENTITY
import com.hikarisource.smarthabits.util.TestData.WEEKLY_PERIODICITY_AS_STRING
import com.hikarisource.smarthabits.util.TestData.WEEKLY_PERIODICITY_ENTITY
import com.hikarisource.smarthabits.util.TestData.YEARLY_PERIODICITY_AS_STRING
import com.hikarisource.smarthabits.util.TestData.YEARLY_PERIODICITY_ENTITY
import org.junit.Assert
import org.junit.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun toDailyPeriodicity() {
        val dailyPeriodicity = converters.toPeriodicity(DAILY_PERIODICITY_AS_STRING)
        Assert.assertEquals(DAILY_PERIODICITY_ENTITY, dailyPeriodicity)
    }

    @Test
    fun fromDailyPeriodicity() {
        val dailyPeriodicityAsString = converters.fromPeriodicity(DAILY_PERIODICITY_ENTITY)
        Assert.assertEquals(DAILY_PERIODICITY_AS_STRING, dailyPeriodicityAsString)
    }

    @Test
    fun toWeeklyPeriodicity() {
        val weeklyPeriodicity = converters.toPeriodicity(WEEKLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(WEEKLY_PERIODICITY_ENTITY, weeklyPeriodicity)
    }

    @Test
    fun fromWeeklyPeriodicity() {
        val weeklyPeriodicityAsString = converters.fromPeriodicity(WEEKLY_PERIODICITY_ENTITY)
        Assert.assertEquals(WEEKLY_PERIODICITY_AS_STRING, weeklyPeriodicityAsString)
    }

    @Test
    fun toMonthlyPeriodicity() {
        val monthlyPeriodicity = converters.toPeriodicity(MONTHLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(MONTHLY_PERIODICITY_ENTITY, monthlyPeriodicity)
    }

    @Test
    fun fromMonthlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.fromPeriodicity(MONTHLY_PERIODICITY_ENTITY)
        Assert.assertEquals(MONTHLY_PERIODICITY_AS_STRING, monthlyPeriodicityAsString)
    }

    @Test
    fun toYearlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.toPeriodicity(YEARLY_PERIODICITY_AS_STRING)
        Assert.assertEquals(YEARLY_PERIODICITY_ENTITY, monthlyPeriodicityAsString)
    }

    @Test
    fun fromYearlyPeriodicity() {
        val monthlyPeriodicityAsString = converters.fromPeriodicity(YEARLY_PERIODICITY_ENTITY)
        Assert.assertEquals(YEARLY_PERIODICITY_AS_STRING, monthlyPeriodicityAsString)
    }
}
