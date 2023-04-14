package com.hikarisource.smarthabits.presentation.viewmodel

import com.hikarisource.smarthabits.presentation.features.list.viewmodel.PeriodicHabitResult
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
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