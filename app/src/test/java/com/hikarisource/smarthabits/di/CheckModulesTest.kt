package com.hikarisource.smarthabits.di

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.hikarisource.smarthabits.common.di.AppModules
import io.mockk.mockkObject
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.test.check.checkKoinModules
import org.koin.test.mock.MockProviderRule

@Ignore
class CheckModulesTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkObject(clazz.java)
    }

    @Test
    fun `test DI modules`() {
        checkKoinModules(AppModules.modules) {
            withInstance<Context>()
            withInstance<Application>()
            withInstance<SavedStateHandle>()
        }
    }
}