package com.sibela.smarthabits.domain.common

import org.junit.Assert
import org.junit.Test

class ResultTest {

    @Test
    fun toError() {
        val throwable = Throwable("Message")
        val error = throwable.toError<String>()
        Assert.assertEquals(throwable, error.throwable)
    }

    @Test
    fun toSuccess() {
        val name = "Leonardo"
        val success = name.toSuccess()
        Assert.assertEquals(name, success.data)
    }
}