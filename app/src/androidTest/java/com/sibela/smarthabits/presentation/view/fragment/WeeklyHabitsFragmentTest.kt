package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class WeeklyHabitsFragmentTest :
    BaseUiTests<WeeklyHabitsFragmentTestRobotArrange, WeeklyHabitsFragmentTestRobotAct, WeeklyHabitsFragmentTestRobotAssert>() {

    override val arrange: WeeklyHabitsFragmentTestRobotArrange
        get() = WeeklyHabitsFragmentTestRobotArrange()

    override val act: WeeklyHabitsFragmentTestRobotAct
        get() = WeeklyHabitsFragmentTestRobotAct()

    override val assert: WeeklyHabitsFragmentTestRobotAssert
        get() = WeeklyHabitsFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.weeklyHabitFragmentModule)

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