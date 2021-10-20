package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class HabitsDailyFragmentTest :
    BaseUiTests<HabitsDailyFragmentTestRobotArrange, HabitsDailyFragmentTestRobotAct, HabitsDailyFragmentTestRobotAssert>() {

    override val arrange: HabitsDailyFragmentTestRobotArrange
        get() = HabitsDailyFragmentTestRobotArrange()

    override val act: HabitsDailyFragmentTestRobotAct
        get() = HabitsDailyFragmentTestRobotAct()

    override val assert: HabitsDailyFragmentTestRobotAssert
        get() = HabitsDailyFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.habitsDailyFragmentModule)

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