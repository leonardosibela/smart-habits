package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_WEEKLY_HABIT_ENTITY
import org.junit.Assert
import org.junit.Test

class WeeklyHabitMapperImplTest {

    private val mapper = WeeklyHabitMapperImpl()

    @Test
    fun toDomain() {
        val expectHabit = FIRST_WEEKLY_HABIT
        val actualHabit = mapper.toDomain(FIRST_WEEKLY_HABIT_ENTITY)
        Assert.assertEquals(expectHabit, actualHabit)
    }

    @Test
    fun fromDomain() {
        val expectHabit = FIRST_WEEKLY_HABIT_ENTITY
        val actualHabit = mapper.fromDomain(FIRST_WEEKLY_HABIT)
        Assert.assertEquals(expectHabit, actualHabit)
    }
}