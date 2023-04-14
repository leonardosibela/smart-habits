package com.hikarisource.smarthabits.presentation.view.fragment

import com.hikarisource.smarthabits.di.DependenciesManager
import com.hikarisource.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class EditHabitFragmentTest :
    BaseUiTests<EditHabitFragmentTestRobotArrange, EditHabitFragmentTestRobotAct, EditHabitFragmentTestRobotAssert>() {

    override val arrange: EditHabitFragmentTestRobotArrange
        get() = EditHabitFragmentTestRobotArrange()

    override val act: EditHabitFragmentTestRobotAct
        get() = EditHabitFragmentTestRobotAct()

    override val assert: EditHabitFragmentTestRobotAssert
        get() = EditHabitFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.editHabitFragmentModule)

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
            habitDescriptionHasCorrectText()
            editHabitIsEnabled()
        }
    }
}