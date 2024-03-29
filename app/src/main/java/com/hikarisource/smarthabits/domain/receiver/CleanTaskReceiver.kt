package com.hikarisource.smarthabits.domain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hikarisource.smarthabits.common.di.inject
import com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmScheduler
import com.hikarisource.smarthabits.domain.usecase.ResetHabitsUseCase

class CleanTaskReceiver : BroadcastReceiver() {

    private val resetHabitsUseCase: ResetHabitsUseCase by inject()
    private val alarmScheduler: CleanTaskAlarmScheduler by inject()

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        resetHabitsUseCase.invoke()
        alarmScheduler.schedule()
    }
}
