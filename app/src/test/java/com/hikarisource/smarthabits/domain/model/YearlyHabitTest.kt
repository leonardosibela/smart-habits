package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class YearlyHabitTest {

    @Test
    fun instantiating() {
        val id = 1
        val description = "description"
        val completed = false
        val period = 2
        val yearlyHabit = YearlyHabit(id, description, completed, period)
        Assert.assertEquals(id, yearlyHabit.id)
        Assert.assertEquals(description, yearlyHabit.description)
        Assert.assertEquals(completed, yearlyHabit.completed)
        Assert.assertEquals(period, yearlyHabit.period)
    }
}