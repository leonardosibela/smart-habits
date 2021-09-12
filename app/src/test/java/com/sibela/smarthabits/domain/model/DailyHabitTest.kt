package com.sibela.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class DailyHabitTest {

    @Test
    fun instantiating() {
        val id = 1
        val description = "description"
        val completed = false
        val period = 2
        val dailyHabit = DailyHabit(id, description, completed, period)
        Assert.assertEquals(id, dailyHabit.id)
        Assert.assertEquals(description, dailyHabit.description)
        Assert.assertEquals(completed, dailyHabit.completed)
        Assert.assertEquals(period, dailyHabit.period)
    }
}