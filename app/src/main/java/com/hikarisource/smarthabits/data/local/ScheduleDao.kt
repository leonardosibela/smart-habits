package com.hikarisource.smarthabits.data.local

import androidx.room.*
import com.hikarisource.smarthabits.data.entity.ScheduleDateEntity

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(scheduleDateEntity: ScheduleDateEntity)

    @Query("SELECT * FROM scheduleDate WHERE id = 1")
    suspend fun getAll(): List<ScheduleDateEntity>
}