package com.sibela.smarthabits.presentation.view.activity

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.rule.KoinTestRule
import com.sibela.smarthabits.presentation.view.fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val modules = DependenciesManager

    @get:Rule
    @OptIn(ExperimentalCoroutinesApi::class)
    val koinTestRule: KoinTestRule by lazy {
        KoinTestRule(
            listOf(
                modules.addPeriodicHabitFragmentModule,
                modules.editHabitFragmentModule,
                modules.dailyHabitFragmentModule,
                modules.habitsDailyFragmentModule,
                modules.habitsMonthlyFragmentModule,
                modules.habitsWeeklyFragmentModule,
                modules.habitsYearlyFragmentModule,
                modules.monthlyHabitFragmentModule,
                modules.settingsFragmentModule,
                modules.weeklyHabitFragmentModule,
                modules.yearlyHabitFragmentModule
            )
        )
    }

    private fun mainActivityArrange(func: MainActivityTestRobotsArrange.() -> Unit) =
        MainActivityTestRobotsArrange().func()

    private fun mainActivityAct(func: MainActivityTestRobotsAct.() -> Unit) =
        MainActivityTestRobotsAct().func()

    private fun mainActivityAssert(func: MainActivityTestRobotsAssert.() -> Unit) =
        MainActivityTestRobotsAssert().func()

    private fun addPeriodicHabitArrange(func: AddPeriodicHabitFragmentTestRobotArrange.() -> Unit) =
        AddPeriodicHabitFragmentTestRobotArrange().func()

    private fun addPeriodicHabitAct(func: AddPeriodicHabitFragmentTestRobotAct.() -> Unit) =
        AddPeriodicHabitFragmentTestRobotAct().func()

    private fun addPeriodicHabitAssert(func: AddPeriodicHabitFragmentTestRobotAssert.() -> Unit) =
        AddPeriodicHabitFragmentTestRobotAssert().func()

    private fun dailyHabitsArrange(func: DailyHabitsFragmentTestRobotArrange.() -> Unit) =
        DailyHabitsFragmentTestRobotArrange().func()

    private fun dailyHabitsAct(func: DailyHabitsFragmentTestRobotAct.() -> Unit) =
        DailyHabitsFragmentTestRobotAct().func()

    private fun dailyHabitsAssert(func: DailyHabitsFragmentTestRobotAssert.() -> Unit) =
        DailyHabitsFragmentTestRobotAssert().func()

    private fun editHabitArrange(func: EditHabitFragmentTestRobotArrange.() -> Unit) =
        EditHabitFragmentTestRobotArrange().func()

    private fun editHabitAct(func: EditHabitFragmentTestRobotAct.() -> Unit) =
        EditHabitFragmentTestRobotAct().func()

    private fun editHabitAssert(func: EditHabitFragmentTestRobotAct.() -> Unit) =
        EditHabitFragmentTestRobotAct().func()

    private fun habitsDailyArrange(func: HabitsDailyFragmentTestRobotArrange.() -> Unit) =
        HabitsDailyFragmentTestRobotArrange().func()

    private fun habitsDailyAct(func: HabitsDailyFragmentTestRobotAct.() -> Unit) =
        HabitsDailyFragmentTestRobotAct().func()

    private fun habitsDailyAssert(func: HabitsDailyFragmentTestRobotAssert.() -> Unit) =
        HabitsDailyFragmentTestRobotAssert().func()

    private fun habitsMonthlyArrange(func: HabitsMonthlyFragmentTestRobotArrange.() -> Unit) =
        HabitsMonthlyFragmentTestRobotArrange().func()

    private fun habitsMonthlyAct(func: HabitsMonthlyFragmentTestRobotAct.() -> Unit) =
        HabitsMonthlyFragmentTestRobotAct().func()

    private fun habitsMonthlyAssert(func: HabitsMonthlyFragmentTestRobotAssert.() -> Unit) =
        HabitsMonthlyFragmentTestRobotAssert().func()

    private fun habitsWeeklyArrange(func: HabitsWeeklyFragmentTestRobotArrange.() -> Unit) =
        HabitsWeeklyFragmentTestRobotArrange().func()

    private fun habitsWeeklyAct(func: HabitsWeeklyFragmentTestRobotAct.() -> Unit) =
        HabitsWeeklyFragmentTestRobotAct().func()

    private fun habitsWeeklyAssert(func: HabitsWeeklyFragmentTestRobotAssert.() -> Unit) =
        HabitsWeeklyFragmentTestRobotAssert().func()

    private fun habitsYearlyArrange(func: HabitsYearlyFragmentTestRobotArrange.() -> Unit) =
        HabitsYearlyFragmentTestRobotArrange().func()

    private fun habitsYearlyAct(func: HabitsYearlyFragmentTestRobotAct.() -> Unit) =
        HabitsYearlyFragmentTestRobotAct().func()

    private fun habitsYearlyAssert(func: HabitsYearlyFragmentTestRobotAssert.() -> Unit) =
        HabitsYearlyFragmentTestRobotAssert().func()

    private fun monthlyHabitsArrange(func: MonthlyHabitsFragmentTestRobotArrange.() -> Unit) =
        MonthlyHabitsFragmentTestRobotArrange().func()

    private fun monthlyHabitsAct(func: MonthlyHabitsFragmentTestRobotAct.() -> Unit) =
        MonthlyHabitsFragmentTestRobotAct().func()

    private fun monthlyHabitsAssert(func: MonthlyHabitsFragmentTestRobotAssert.() -> Unit) =
        MonthlyHabitsFragmentTestRobotAssert().func()

    private fun settingsArrange(func: SettingsFragmentTestRobotArrange.() -> Unit) =
        SettingsFragmentTestRobotArrange().func()

    private fun settingsAct(func: SettingsFragmentTestRobotAct.() -> Unit) =
        SettingsFragmentTestRobotAct().func()

    private fun settingsAssert(func: SettingsFragmentTestRobotAssert.() -> Unit) =
        SettingsFragmentTestRobotAssert().func()

    private fun weeklyHabitsArrange(func: WeeklyHabitsFragmentTestRobotArrange.() -> Unit) =
        WeeklyHabitsFragmentTestRobotArrange().func()

    private fun weeklyHabitsAct(func: WeeklyHabitsFragmentTestRobotAct.() -> Unit) =
        WeeklyHabitsFragmentTestRobotAct().func()

    private fun weeklyHabitsAssert(func: WeeklyHabitsFragmentTestRobotAssert.() -> Unit) =
        WeeklyHabitsFragmentTestRobotAssert().func()

    private fun yearlyHabitsArrange(func: YearlyHabitsFragmentTestRobotArrange.() -> Unit) =
        YearlyHabitsFragmentTestRobotArrange().func()

    private fun yearlyHabitsAct(func: YearlyHabitsFragmentTestRobotAct.() -> Unit) =
        YearlyHabitsFragmentTestRobotAct().func()

    private fun yearlyHabitsAssert(func: YearlyHabitsFragmentTestRobotAssert.() -> Unit) =
        YearlyHabitsFragmentTestRobotAssert().func()

    @Test
    fun check_static_data() {
        mainActivityArrange {
            openActivity()
        }
        mainActivityAssert {
            viewsHaveCorrectText()
        }
    }
}