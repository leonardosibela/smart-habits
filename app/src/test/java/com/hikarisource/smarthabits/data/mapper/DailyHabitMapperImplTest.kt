package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_DAILY_HABIT_ENTITY
import org.junit.Assert
import org.junit.Test

class DailyHabitMapperImplTest {

    private val mapper = DailyHabitMapperImpl()

    @Test
    fun toDomain() {
        val expectHabit = FIRST_DAILY_HABIT
        val actualHabit = mapper.toDomain(FIRST_DAILY_HABIT_ENTITY)
        Assert.assertEquals(expectHabit, actualHabit)
    }

    @Test
    fun fromDomain() {
        val expectHabit = FIRST_DAILY_HABIT_ENTITY
        val actualHabit = mapper.fromDomain(FIRST_DAILY_HABIT)
        Assert.assertEquals(expectHabit, actualHabit)
    }
}
