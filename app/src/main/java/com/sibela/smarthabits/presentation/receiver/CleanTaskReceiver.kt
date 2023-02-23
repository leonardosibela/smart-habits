package com.sibela.smarthabits.presentation.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.domain.usecase.*
import com.sibela.smarthabits.presentation.alarm.CleanTaskAlarmScheduler
import org.koin.java.KoinJavaComponent.inject

class CleanTaskReceiver : BroadcastReceiver() {

    private val resetHabitsUseCase: ResetHabitsUseCase by inject(ResetHabitsUseCase::class.java)
    private val alarmScheduler: CleanTaskAlarmScheduler by inject(CleanTaskAlarmScheduler::class.java)

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        resetHabitsUseCase.invoke()
        alarmScheduler.schedule()
    }
}