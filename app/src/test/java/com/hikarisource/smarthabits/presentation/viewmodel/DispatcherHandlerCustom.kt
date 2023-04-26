package com.hikarisource.smarthabits.presentation.viewmodel

import com.hikarisource.smarthabits.presentation.features.common.viewmodel.DispatcherHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DispatcherHandlerCustom(
    override val Default: CoroutineDispatcher,
    override val IO: CoroutineDispatcher,
    override val Main: CoroutineDispatcher,
    override val Unconfined: CoroutineDispatcher
) : DispatcherHandler {

    constructor(coroutineDispatcher: CoroutineDispatcher) : this(
        coroutineDispatcher,
        coroutineDispatcher,
        coroutineDispatcher,
        coroutineDispatcher
    )
}
