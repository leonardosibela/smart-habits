package com.hikarisource.smarthabits.presentation.view.fragment

import com.hikarisource.smarthabits.di.DependenciesManager
import com.hikarisource.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class WeeklyHabitsSettingsFragmentTest :
    BaseUiTests<HabitsWeeklyFragmentTestRobotArrange, HabitsWeeklyFragmentTestRobotAct, HabitsWeeklyFragmentTestRobotAssert>() {

    override val arrange: HabitsWeeklyFragmentTestRobotArrange
        get() = HabitsWeeklyFragmentTestRobotArrange()

    override val act: HabitsWeeklyFragmentTestRobotAct
        get() = HabitsWeeklyFragmentTestRobotAct()

    override val assert: HabitsWeeklyFragmentTestRobotAssert
        get() = HabitsWeeklyFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.habitsWeeklyFragmentModule)

    @Test
    fun check_static_data() {
        arrange {
            openFragment()
        }
        assert {
            viewsAreDisplayed()
            viewsAreVisible()
            viewsAreGone()
            viewsHaveCorrectText()
            addHabitIsEnabled()
        }
    }
}