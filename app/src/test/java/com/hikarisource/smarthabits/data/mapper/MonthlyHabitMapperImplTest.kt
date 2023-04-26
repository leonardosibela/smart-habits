package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_MONTHLY_HABIT_ENTITY
import org.junit.Assert
import org.junit.Test

class MonthlyHabitMapperImplTest {

    private val mapper = MonthlyHabitMapperImpl()

    @Test
    fun toDomain() {
        val expectHabit = FIRST_MONTHLY_HABIT
        val actualHabit = mapper.toDomain(FIRST_MONTHLY_HABIT_ENTITY)
        Assert.assertEquals(expectHabit, actualHabit)
    }

    @Test
    fun fromDomain() {
        val expectHabit = FIRST_MONTHLY_HABIT_ENTITY.copy()
        val actualHabit = mapper.fromDomain(FIRST_MONTHLY_HABIT.copy())
        Assert.assertEquals(expectHabit, actualHabit)
    }
}
