package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class DailyHabitsFragmentTest :
    BaseUiTests<DailyHabitsFragmentTestRobotArrange, DailyHabitsFragmentTestRobotAct, DailyHabitsFragmentTestRobotAssert>() {

    override val arrange: DailyHabitsFragmentTestRobotArrange
        get() = DailyHabitsFragmentTestRobotArrange()

    override val act: DailyHabitsFragmentTestRobotAct
        get() = DailyHabitsFragmentTestRobotAct()

    override val assert: DailyHabitsFragmentTestRobotAssert
        get() = DailyHabitsFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.dailyHabitFragmentModule)

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