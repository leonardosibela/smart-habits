package com.hikarisource.smarthabits.presentation.rule

import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
class KoinTestRule(private val modules: List<Module>) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(modules)
        }
    }

    override fun finished(description: Description) {
        super.finished(description)
        stopKoin()
    }
}
