package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class MonthlyHabitTest {

    @Test
    fun instantiating() {
        val id = 1
        val description = "description"
        val completed = false
        val period = 2
        val monthlyHabit = MonthlyHabit(id, description, completed, period)
        Assert.assertEquals(id, monthlyHabit.id)
        Assert.assertEquals(description, monthlyHabit.description)
        Assert.assertEquals(completed, monthlyHabit.completed)
        Assert.assertEquals(period, monthlyHabit.period)
    }
}
