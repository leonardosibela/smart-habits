package com.hikarisource.smarthabits.domain.common

sealed class Result<T>(val value: T? = null, val error: Throwable? = null) {
    data class Success<T>(val data: T) : Result<T>(data)
    data class Error<T>(val throwable: Throwable) : Result<T>(error = throwable)
}

inline fun <T> resultBy(scope: () -> T?): Result<T> = try {
    val data = scope.invoke()
    if (data != null)
        Result.Success(data)
    else
        Result.Error(Exception("Result is null"))
} catch (error: Throwable) {
    Result.Error(error)
}

fun <T> Throwable.toError() = Result.Error<T>(this)
fun <T> T.toSuccess() = Result.Success(this)
