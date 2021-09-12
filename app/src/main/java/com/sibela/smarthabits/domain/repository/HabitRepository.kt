package com.sibela.smarthabits.domain.repository

import com.sibela.smarthabits.domain.model.Habit

interface HabitRepository {

    suspend fun save(habit: Habit)
    suspend fun getAllHabitsThatAreDaily(): List<Habit>
    suspend fun getAllHabitsThatAreWeekly(): List<Habit>
    suspend fun getAllHabitsThatAreMonthly(): List<Habit>
    suspend fun getAllHabitsThatAreYearly(): List<Habit>
    suspend fun delete(habit: Habit)
    suspend fun editHabitDescription(id: Int, newDescription: String)
}