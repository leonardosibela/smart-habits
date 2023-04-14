package com.hikarisource.smarthabits.presentation.view.fragment

import com.hikarisource.smarthabits.di.DependenciesManager
import com.hikarisource.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class AddPeriodicHabitFragmentTest :
    BaseUiTests<AddPeriodicHabitFragmentTestRobotArrange, AddPeriodicHabitFragmentTestRobotAct, AddPeriodicHabitFragmentTestRobotAssert>() {

    override val arrange: AddPeriodicHabitFragmentTestRobotArrange
        get() = AddPeriodicHabitFragmentTestRobotArrange()

    override val act: AddPeriodicHabitFragmentTestRobotAct
        get() = AddPeriodicHabitFragmentTestRobotAct()

    override val assert: AddPeriodicHabitFragmentTestRobotAssert
        get() = AddPeriodicHabitFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.addPeriodicHabitFragmentModule)

    @Test
    fun check_static_data() {
        arrange {
            openFragment()
        }
        assert {
            viewsAreDisplayed()
            viewsAreVisible()
            habitDescriptionHasCorrectHint()
            editHabitButtonHasCorrectText()
            habitDescriptionIsEmpty()
            addHabitIsEnabled()
        }
    }
}