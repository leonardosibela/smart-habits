package com.sibela.smarthabits.presentation.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.extension.getNextDayAtMidnight
import com.sibela.smarthabits.presentation.constants.RequestCode
import com.sibela.smarthabits.presentation.receiver.CleanTaskReceiver
import java.util.Calendar

class CleanTaskAlarmSchedulerImpl(private val context: Context) : CleanTaskAlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule() {
        val intent = Intent(context, CleanTaskReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RequestCode.CLEAR_TASK,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val nextDayMidnight: Calendar = Calendar.getInstance().getNextDayAtMidnight()

        alarmManager.cancel(pendingIntent)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextDayMidnight.timeInMillis,
            pendingIntent
        )
    }
}