package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class PeriodicityTest {

    @Test
    fun checkAmount() {
        Assert.assertEquals(4, Periodicity.values().size)
    }

    @Test
    fun daily() {
        Assert.assertEquals("DAILY", Periodicity.DAILY.name)
    }

    @Test
    fun weekly() {
        Assert.assertEquals("WEEKLY", Periodicity.WEEKLY.name)
    }

    @Test
    fun monthly() {
        Assert.assertEquals("MONTHLY", Periodicity.MONTHLY.name)
    }

    @Test
    fun yearly() {
        Assert.assertEquals("YEARLY", Periodicity.YEARLY.name)
    }
}
