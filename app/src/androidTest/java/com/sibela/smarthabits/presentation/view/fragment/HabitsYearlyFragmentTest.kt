package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class HabitsYearlyFragmentTest :
    BaseUiTests<HabitsYearlyFragmentTestRobotArrange, HabitsYearlyFragmentTestRobotAct, HabitsYearlyFragmentTestRobotAssert>() {

    override val arrange: HabitsYearlyFragmentTestRobotArrange
        get() = HabitsYearlyFragmentTestRobotArrange()

    override val act: HabitsYearlyFragmentTestRobotAct
        get() = HabitsYearlyFragmentTestRobotAct()

    override val assert: HabitsYearlyFragmentTestRobotAssert
        get() = HabitsYearlyFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.habitsYearlyFragmentModule)

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