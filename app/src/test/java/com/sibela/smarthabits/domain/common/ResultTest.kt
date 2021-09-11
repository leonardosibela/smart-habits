package com.sibela.smarthabits.domain.common

import org.junit.Assert
import org.junit.Test

class ResultTest {

    private companion object {
        const val RESULT_IS_NULL = "Result is null"
        const val NAME = "Leonardo"
        const val MESSAGE = "Message"
    }

    @Test
    fun toError() {
        val throwable = Throwable(MESSAGE)
        val error = throwable.toError<String>()
        Assert.assertEquals(throwable, error.throwable)
    }

    @Test
    fun toSuccess() {
        val name = NAME
        val success = name.toSuccess()
        Assert.assertEquals(name, success.data)
    }

    @Test
    fun `resultBy error`() {
        val throwable = Throwable(MESSAGE)
        val result = resultBy {
            throw throwable
        }
        Assert.assertEquals(Result.Error<String>(throwable), result)
    }

    @Test
    fun `resultBy success`() {
        val name = NAME
        val result = resultBy {
            name
        }
        Assert.assertEquals(Result.Success(name), result)
    }

    @Test
    fun `resultBy null`() {
        val nullVal: String? = null
        val result: Result<String?> = resultBy {
            nullVal
        }
        Assert.assertTrue(result is Result.Error<String?>)
        Assert.assertEquals(RESULT_IS_NULL, result.error?.message)
    }
}