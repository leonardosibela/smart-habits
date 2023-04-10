package com.sibela.smarthabits.domain.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.di.inject
import com.sibela.smarthabits.domain.receiver.CleanTaskReceiver
import com.sibela.smarthabits.domain.usecase.SetLastScheduleDateUseCase
import com.sibela.smarthabits.extension.getNextDayAtMidnight
import com.sibela.smarthabits.presentation.constants.RequestCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId

class CleanTaskAlarmSchedulerImpl(private val context: Context) : CleanTaskAlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    private val setLastScheduleDateUseCase: SetLastScheduleDateUseCase by inject()

    override fun schedule() {
        val intent = Intent(context, CleanTaskReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            RequestCode.CLEAR_TASK,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val nextDayMidnight: LocalDateTime = LocalDateTime.now().getNextDayAtMidnight()

        alarmManager.cancel(pendingIntent)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextDayMidnight.atZone(ZoneId.systemDefault()).toEpochSecond(),
            pendingIntent
        )

        CoroutineScope(Dispatchers.IO).launch { setLastScheduleDateUseCase.invoke(nextDayMidnight) }
    }
}