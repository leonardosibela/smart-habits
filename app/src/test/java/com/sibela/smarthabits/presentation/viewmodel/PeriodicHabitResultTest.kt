package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
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
}