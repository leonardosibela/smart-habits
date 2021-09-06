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

    @Test
    fun `resultBy error`() {
        val throwable = Throwable("Message")
        val result = resultBy {
            throw throwable
        }
        Assert.assertEquals(Result.Error<String>(throwable), result)
    }

    @Test
    fun `resultBy success`() {
        val name = "Leonardo"
        val result = resultBy {
            name
        }
        Assert.assertEquals(Result.Success(name), result)
    }
}