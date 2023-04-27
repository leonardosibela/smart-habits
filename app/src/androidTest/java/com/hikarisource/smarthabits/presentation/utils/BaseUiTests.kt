package com.hikarisource.smarthabits.presentation.utils

import com.hikarisource.smarthabits.presentation.rule.KoinTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.core.module.Module

abstract class BaseUiTests<Arrange, Act, Assert> {

    abstract val arrange: Arrange
    abstract val act: Act
    abstract val assert: Assert
    abstract val modules: List<Module>

    protected fun arrange(func: Arrange.() -> Unit) = arrange.func()
    protected fun act(func: Act.() -> Unit) = act.func()
    protected fun assert(func: Assert.() -> Unit) = assert.func()

    @get:Rule
    @OptIn(ExperimentalCoroutinesApi::class)
    val koinTestRule: KoinTestRule by lazy { KoinTestRule(modules) }
}
