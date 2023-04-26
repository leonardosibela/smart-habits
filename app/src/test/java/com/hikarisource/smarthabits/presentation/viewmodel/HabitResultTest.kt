package com.hikarisource.smarthabits.presentation.viewmodel

import com.hikarisource.smarthabits.presentation.features.settings.viewmodel.HabitResult
import com.hikarisource.smarthabits.util.TestData.FIRST_HABIT_DAILY
import com.hikarisource.smarthabits.util.TestData.SECOND_HABIT_DAILY
import org.junit.Assert
import org.junit.Test

class HabitResultTest {

    @Test
    fun success() {
        val habits = listOf(FIRST_HABIT_DAILY, SECOND_HABIT_DAILY)
        val success = HabitResult.Success(habits)
        Assert.assertEquals(habits, success.data)
    }

    @Test
    fun error() {
        val throwable = Throwable("Message")
        val error = HabitResult.Error(throwable)
        Assert.assertEquals(throwable, error.data)
    }
}
