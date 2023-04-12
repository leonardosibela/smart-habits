package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class MonthlyHabitsSettingsFragmentTest :
    BaseUiTests<HabitsMonthlyFragmentTestRobotArrange, HabitsMonthlyFragmentTestRobotAct, HabitsMonthlyFragmentTestRobotAssert>() {

    override val arrange: HabitsMonthlyFragmentTestRobotArrange
        get() = HabitsMonthlyFragmentTestRobotArrange()

    override val act: HabitsMonthlyFragmentTestRobotAct
        get() = HabitsMonthlyFragmentTestRobotAct()

    override val assert: HabitsMonthlyFragmentTestRobotAssert
        get() = HabitsMonthlyFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.habitsMonthlyFragmentModule)

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