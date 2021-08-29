package com.sibela.smarthabits.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
@OptIn(ObsoleteCoroutinesApi::class)
class CoroutineTestRule : TestWatcher() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}