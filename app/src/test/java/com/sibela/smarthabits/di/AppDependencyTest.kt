package com.sibela.smarthabits.di

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class AppDependencyTest {

    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkObject(clazz.java)
    }

    @Before
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check module`() {
        val mockContext = mockk<Application>()
        koinApplication {
            androidContext(mockContext)
            modules(AppModules.modules)
        }.checkModules {
            defaultValue<SavedStateHandle>(mockk())
        }
    }
}