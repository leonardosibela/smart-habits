package com.sibela.smarthabits.domain.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.VisibleForTesting
import com.sibela.smarthabits.common.di.inject
import com.sibela.smarthabits.common.extension.hasDayAheadOf
import com.sibela.smarthabits.common.extension.hasMonthAheadOf
import com.sibela.smarthabits.common.extension.hasWeekAheadOf
import com.sibela.smarthabits.common.extension.hasYearAheadOf
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.extension.*
import java.time.LocalDateTime

class BootCompleteReceiver : BroadcastReceiver() {

    private val getLastScheduleDateUseCase: GetLastScheduleDateUseCase by inject()
    private val cleanTaskAlarmScheduler: com.sibela.smarthabits.domain.alarm.CleanTaskAlarmScheduler by inject()

    private val resetDailyHabitUseCase: ResetHabitsUseCase by inject()
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase by inject()
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase by inject()
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase by inject()

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        onReceive(intent)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun onReceive(intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return

        val lastScheduleDate = getLastScheduleDateUseCase.invoke()!!
        val currentDate = LocalDateTime.now()

        if (currentDate hasDayAheadOf lastScheduleDate) {
            resetDailyHabitUseCase.invoke()
        }

        if (currentDate hasWeekAheadOf lastScheduleDate) {
            resetWeeklyHabitsUseCase.invoke()
        }

        if (currentDate hasMonthAheadOf lastScheduleDate) {
            resetMonthlyHabitsUseCase.invoke()
        }

        if (currentDate hasYearAheadOf lastScheduleDate) {
            resetYearlyHabitsUseCase.invoke()
        }

        cleanTaskAlarmScheduler.schedule()
    }
}