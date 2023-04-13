package com.sibela.smarthabits.domain.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.common.di.inject
import com.sibela.smarthabits.domain.usecase.*

class CleanTaskReceiver : BroadcastReceiver() {

    private val resetHabitsUseCase: ResetHabitsUseCase by inject()
    private val alarmScheduler: com.sibela.smarthabits.domain.alarm.CleanTaskAlarmScheduler by inject()

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        resetHabitsUseCase.invoke()
        alarmScheduler.schedule()
    }
}