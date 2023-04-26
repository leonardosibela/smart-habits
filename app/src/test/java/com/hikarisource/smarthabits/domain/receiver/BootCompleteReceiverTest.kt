package com.hikarisource.smarthabits.domain.receiver

import android.content.Intent
import com.hikarisource.smarthabits.domain.alarm.CleanTaskAlarmScheduler
import com.hikarisource.smarthabits.domain.usecase.GetLastScheduleDateUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.hikarisource.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import com.hikarisource.smarthabits.util.initMockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.verify
import java.time.LocalDateTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@Suppress("MaxLineLength")
class BootCompleteReceiverTest {

    @RelaxedMockK
    private lateinit var getLastScheduleDateUseCase: GetLastScheduleDateUseCase

    @RelaxedMockK
    private lateinit var cleanTaskAlarmScheduler: CleanTaskAlarmScheduler

    @RelaxedMockK
    private lateinit var resetDailyHabitUseCase: ResetDailyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase

    @RelaxedMockK
    private lateinit var resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase

    private val bootCompleteReceiver: BootCompleteReceiver

    init {
        initMockKAnnotations()
        startKoin {
            androidContext(mockk())
            modules(
                module {
                    single { getLastScheduleDateUseCase }
                    single { cleanTaskAlarmScheduler }

                    single { resetDailyHabitUseCase }
                    single { resetWeeklyHabitsUseCase }
                    single { resetMonthlyHabitsUseCase }
                    single { resetYearlyHabitsUseCase }
                }
            )
        }

        bootCompleteReceiver = BootCompleteReceiver()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `GIVEN currentDate hasDayAheadOf lastScheduleDate WHEN onReceive called THEN must only invoke resetDailyHabitUseCase`() =
        runTest {
            val bootCompletedIntent = spyk(Intent())
            every { bootCompletedIntent.action } returns Intent.ACTION_BOOT_COMPLETED

            val lastScheduleDate = LocalDateTime.of(2023, 2, 1, 0, 0, 0)
            coEvery { getLastScheduleDateUseCase.invoke() } returns lastScheduleDate

            mockkStatic(LocalDateTime::class)

            val currentDate = LocalDateTime.of(2023, 2, 2, 0, 0, 0)

            every { LocalDateTime.now() } returns currentDate

            bootCompleteReceiver.onReceive(bootCompletedIntent)

            verify(exactly = 1) { cleanTaskAlarmScheduler.schedule() }

            coVerify(exactly = 1) { resetDailyHabitUseCase.invoke() }
            coVerify(exactly = 0) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDate hasWeekAheadOf lastScheduleDate WHEN onReceive called THEN must invoke resetDailyHabitUseCase and resetWeeklyHabitsUseCase`() =
        runTest {
            val bootCompletedIntent = spyk(Intent())
            every { bootCompletedIntent.action } returns Intent.ACTION_BOOT_COMPLETED

            val lastScheduleDate = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
            coEvery { getLastScheduleDateUseCase.invoke() } returns lastScheduleDate

            mockkStatic(LocalDateTime::class)
            val currentDate = LocalDateTime.of(2023, 1, 9, 0, 0, 0)
            every { LocalDateTime.now() } returns currentDate

            bootCompleteReceiver.onReceive(bootCompletedIntent)

            verify(exactly = 1) { cleanTaskAlarmScheduler.schedule() }

            coVerify(exactly = 1) { resetDailyHabitUseCase.invoke() }
            coVerify(exactly = 1) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDate hasMonthAheadOf lastScheduleDate WHEN onReceive called THEN must only not invoke resetYearlyHabitUseCase`() =
        runTest {
            val bootCompletedIntent = spyk(Intent())
            every { bootCompletedIntent.action } returns Intent.ACTION_BOOT_COMPLETED

            val lastScheduleDate = LocalDateTime.of(2023, 2, 1, 0, 0, 0)
            coEvery { getLastScheduleDateUseCase.invoke() } returns lastScheduleDate

            mockkStatic(LocalDateTime::class)
            val currentDate = LocalDateTime.of(2023, 3, 1, 0, 0, 0)
            every { LocalDateTime.now() } returns currentDate

            bootCompleteReceiver.onReceive(bootCompletedIntent)

            verify(exactly = 1) { cleanTaskAlarmScheduler.schedule() }

            coVerify(exactly = 1) { resetDailyHabitUseCase.invoke() }
            coVerify(exactly = 1) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 0) { resetYearlyHabitsUseCase.invoke() }
        }

    @Test
    fun `GIVEN currentDate hasYearAheadOf lastScheduleDate WHEN onReceive called THEN must invoke all resets`() =
        runTest {
            val bootCompletedIntent = spyk(Intent())
            every { bootCompletedIntent.action } returns Intent.ACTION_BOOT_COMPLETED

            val lastScheduleDate = LocalDateTime.of(2023, 2, 1, 0, 0, 0)
            coEvery { getLastScheduleDateUseCase.invoke() } returns lastScheduleDate

            mockkStatic(LocalDateTime::class)
            val currentDate = LocalDateTime.of(2024, 2, 1, 0, 0, 0)
            every { LocalDateTime.now() } returns currentDate

            bootCompleteReceiver.onReceive(bootCompletedIntent)

            verify(exactly = 1) { cleanTaskAlarmScheduler.schedule() }

            coVerify(exactly = 1) { resetDailyHabitUseCase.invoke() }
            coVerify(exactly = 1) { resetWeeklyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetMonthlyHabitsUseCase.invoke() }
            coVerify(exactly = 1) { resetYearlyHabitsUseCase.invoke() }
        }
}
