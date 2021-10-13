package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.sibela.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import org.junit.Assert
import org.junit.Test

class PeriodicHabitResultTest {

    @Test
    fun success() {
        val habits = listOf(FIRST_DAILY_HABIT, FIRST_WEEKLY_HABIT)
        val success = PeriodicHabitResult.Success(habits)
        Assert.assertEquals(habits, success.data)
    }

    @Test
    fun error() {
        val throwable = Throwable("Message")
        val error = PeriodicHabitResult.Error(throwable)
        Assert.assertEquals(throwable, error.data)
    }
}