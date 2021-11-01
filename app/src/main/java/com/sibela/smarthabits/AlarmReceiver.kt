package com.sibela.smarthabits

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sibela.smarthabits.domain.usecase.ResetDailyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetMonthlyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetWeeklyHabitsUseCase
import com.sibela.smarthabits.domain.usecase.ResetYearlyHabitsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val resetDailyHabitsUseCase: ResetDailyHabitsUseCase by inject()
    private val resetWeeklyHabitsUseCase: ResetWeeklyHabitsUseCase by inject()
    private val resetMonthlyHabitsUseCase: ResetMonthlyHabitsUseCase by inject()
    private val resetYearlyHabitsUseCase: ResetYearlyHabitsUseCase by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            resetDailyHabitsUseCase.invoke()
            val calendar = Calendar.getInstance()
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                resetWeeklyHabitsUseCase.invoke()
            }
            if (calendar.get(Calendar.DAY_OF_MONTH) == Calendar.JANUARY) {
                resetMonthlyHabitsUseCase.invoke()
            }
            if (calendar.get(Calendar.DAY_OF_YEAR) == 1) {
                resetYearlyHabitsUseCase.invoke()
            }
        }
    }
}