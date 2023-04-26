package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class HabitCounterTest {

    @Test
    fun instantiating() {
        val id = 1
        val periodicity = Periodicity.DAILY
        val period = 2
        val habitCounter = HabitCounter(id, periodicity, period)
        Assert.assertEquals(id, habitCounter.id)
        Assert.assertEquals(periodicity, habitCounter.periodicity)
        Assert.assertEquals(period, habitCounter.period)
    }
}
