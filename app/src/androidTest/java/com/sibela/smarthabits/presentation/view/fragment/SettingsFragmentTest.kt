package com.sibela.smarthabits.presentation.view.fragment

import com.sibela.smarthabits.di.DependenciesManager
import com.sibela.smarthabits.presentation.utils.BaseUiTests
import org.junit.Test
import org.koin.core.module.Module

class SettingsFragmentTest :
    BaseUiTests<SettingsFragmentTestRobotArrange, SettingsFragmentTestRobotAct, SettingsFragmentTestRobotAssert>() {

    override val arrange: SettingsFragmentTestRobotArrange
        get() = SettingsFragmentTestRobotArrange()

    override val act: SettingsFragmentTestRobotAct
        get() = SettingsFragmentTestRobotAct()

    override val assert: SettingsFragmentTestRobotAssert
        get() = SettingsFragmentTestRobotAssert()

    override val modules: List<Module>
        get() = listOf(DependenciesManager.settingsFragmentModule)

    @Test
    fun check_static_data() {
        arrange {
            openFragment()
        }
        assert {
            viewsHaveCorrectText()
        }
    }
}