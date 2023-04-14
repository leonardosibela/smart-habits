package com.hikarisource.smarthabits.presentation.viewmodel

import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
object DispatcherHandlerUnconfined : DispatcherHandler {

    override val Default: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()

    override val IO: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()

    override val Main: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()

    override val Unconfined: CoroutineDispatcher
        get() = UnconfinedTestDispatcher()
}