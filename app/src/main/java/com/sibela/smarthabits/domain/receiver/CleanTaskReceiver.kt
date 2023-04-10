package com.sibela.smarthabits.domain.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.di.inject
import com.sibela.smarthabits.domain.alarm.CleanTaskAlarmScheduler
import com.sibela.smarthabits.domain.usecase.*

class CleanTaskReceiver : BroadcastReceiver() {

    private val resetHabitsUseCase: ResetHabitsUseCase by inject()
    private val alarmScheduler: CleanTaskAlarmScheduler by inject()

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        resetHabitsUseCase.invoke()
        alarmScheduler.schedule()
    }
}