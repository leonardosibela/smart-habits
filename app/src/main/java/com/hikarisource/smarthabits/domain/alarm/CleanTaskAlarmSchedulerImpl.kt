package com.hikarisource.smarthabits.domain.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.hikarisource.smarthabits.common.di.inject
import com.hikarisource.smarthabits.common.extension.getNextDayAtMidnight
import com.hikarisource.smarthabits.domain.receiver.CleanTaskReceiver
import com.hikarisource.smarthabits.domain.usecase.SetLastScheduleDateUseCase
import com.hikarisource.smarthabits.presentation.constants.RequestCode
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
            nextDayMidnight.atZone(ZoneId.systemDefault()).toEpochSecond() * 1_000,
            pendingIntent
        )

        CoroutineScope(Dispatchers.IO).launch { setLastScheduleDateUseCase.invoke(nextDayMidnight) }
    }
}
