package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class WeeklyHabitTest {

    @Test
    fun instantiating() {
        val id = 1
        val description = "description"
        val completed = false
        val period = 2
        val weeklyHabit = WeeklyHabit(id, description, completed, period)
        Assert.assertEquals(id, weeklyHabit.id)
        Assert.assertEquals(description, weeklyHabit.description)
        Assert.assertEquals(completed, weeklyHabit.completed)
        Assert.assertEquals(period, weeklyHabit.period)
    }
}
