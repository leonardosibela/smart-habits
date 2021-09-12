package com.sibela.smarthabits.presentation.viewmodel

import com.sibela.smarthabits.util.TestData.FIRST_HABIT
import com.sibela.smarthabits.util.TestData.SECOND_HABIT
import org.junit.Assert
import org.junit.Test

class HabitResultTest {

    @Test
    fun success() {
        val habits = listOf(FIRST_HABIT, SECOND_HABIT)
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