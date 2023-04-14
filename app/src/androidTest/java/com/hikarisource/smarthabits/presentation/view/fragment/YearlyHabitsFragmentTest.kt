package com.hikarisource.smarthabits.presentation.view.fragment

import com.hikarisource.smarthabits.di.DependenciesManager
import com.hikarisource.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class YearlyHabitsFragmentTest :
    BaseUiTests<YearlyHabitsFragmentTestRobotArrange, YearlyHabitsFragmentTestRobotAct, YearlyHabitsFragmentTestRobotAssert>() {

    override val arrange: YearlyHabitsFragmentTestRobotArrange
        get() = YearlyHabitsFragmentTestRobotArrange()

    override val act: YearlyHabitsFragmentTestRobotAct
        get() = YearlyHabitsFragmentTestRobotAct()

    override val assert: YearlyHabitsFragmentTestRobotAssert
        get() = YearlyHabitsFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.yearlyHabitFragmentModule)

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