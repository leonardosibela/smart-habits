package com.hikarisource.smarthabits.domain.model

import org.junit.Assert
import org.junit.Test

class HabitTest {

    @Test
    fun instantiating() {
        val id = 1
        val description = "description"
        val periodicity = Periodicity.DAILY
        val dailyHabit = Habit(id, description, periodicity)
        Assert.assertEquals(id, dailyHabit.id)
        Assert.assertEquals(description, dailyHabit.description)
        Assert.assertEquals(periodicity, dailyHabit.periodicity)
    }
}