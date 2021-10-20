package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class MonthlyHabitsFragmentTest :
    BaseUiTests<MonthlyHabitsFragmentTestRobotArrange, MonthlyHabitsFragmentTestRobotAct, MonthlyHabitsFragmentTestRobotAssert>() {

    override val arrange: MonthlyHabitsFragmentTestRobotArrange
        get() = MonthlyHabitsFragmentTestRobotArrange()

    override val act: MonthlyHabitsFragmentTestRobotAct
        get() = MonthlyHabitsFragmentTestRobotAct()

    override val assert: MonthlyHabitsFragmentTestRobotAssert
        get() = MonthlyHabitsFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.monthlyHabitFragmentModule)

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