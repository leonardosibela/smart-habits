package com.hikarisource.smarthabits.data.mapper

import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT
import com.hikarisource.smarthabits.util.TestData.FIRST_YEARLY_HABIT_ENTITY
import org.junit.Assert
import org.junit.Test

class YearlyHabitMapperImplTest {

    private val mapper = YearlyHabitMapperImpl()

    @Test
    fun toDomain() {
        val expectHabit = FIRST_YEARLY_HABIT
        val actualHabit = mapper.toDomain(FIRST_YEARLY_HABIT_ENTITY)
        Assert.assertEquals(expectHabit, actualHabit)
    }

    @Test
    fun fromDomain() {
        val expectHabit = FIRST_YEARLY_HABIT_ENTITY
        val actualHabit = mapper.fromDomain(FIRST_YEARLY_HABIT)
        Assert.assertEquals(expectHabit, actualHabit)
    }
}
