package com.hikarisource.smarthabits.presentation.features.common.viewmodel

import kotlinx.coroutines.CoroutineDispatcher

@Suppress("PropertyName", "VariableNaming")
interface DispatcherHandler {

    val Default: CoroutineDispatcher

    val IO: CoroutineDispatcher

    val Main: CoroutineDispatcher

    val Unconfined: CoroutineDispatcher

}
