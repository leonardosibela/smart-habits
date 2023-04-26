package com.hikarisource.smarthabits.util

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {

    private val mainThreadSurrogate = UnconfinedTestDispatcher()

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
