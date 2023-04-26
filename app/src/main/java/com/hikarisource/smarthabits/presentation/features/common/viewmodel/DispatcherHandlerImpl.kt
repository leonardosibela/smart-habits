package com.hikarisource.smarthabits.presentation.features.common.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherHandlerImpl : DispatcherHandler {
    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val Unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
