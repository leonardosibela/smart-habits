package com.hikarisource.smarthabits.domain.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.hikarisource.smarthabits.common.extension.getNextDayAtMidnight
import com.hikarisource.smarthabits.presentation.constants.RequestCode
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId

class CleanTaskAlarmSchedulerImplTest {

    @RelaxedMockK
    private lateinit var pendingIntent: PendingIntent

    private val alarmManager: AlarmManager = mockk(relaxed = true, relaxUnitFun = true)

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var cleanTaskAlarmSchedulerImpl: com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmSchedulerImpl

    @Before
    fun setup() {
        initMockKAnnotations()
        every { context.getSystemService(AlarmManager::class.java) } returns alarmManager
        cleanTaskAlarmSchedulerImpl =
            com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmSchedulerImpl(context)

        mockkStatic(PendingIntent::class)
        every {
            PendingIntent.getBroadcast(
                context,
                RequestCode.CLEAR_TASK,
                any(),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } returns pendingIntent
    }

    @Test
    fun schedule() {
        mockkStatic(LocalDateTime::class)
        val mockedDateTime = mockk<LocalDateTime>(relaxed = true, relaxUnitFun = true)
        val mockedNextDayMidnight = mockk<LocalDateTime>(relaxed = true, relaxUnitFun = true)
        every { mockedDateTime.getNextDayAtMidnight() } returns mockedNextDayMidnight
        every { mockedDateTime.atZone(ZoneId.systemDefault()).toEpochSecond() } returns 1677207600000
        every { LocalDateTime.now() } returns mockedDateTime

        cleanTaskAlarmSchedulerImpl.schedule()

        verify(exactly = 1) { alarmManager.cancel(pendingIntent) }
        verify(exactly = 1) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                any(),
                pendingIntent
            )
        }
    }
}