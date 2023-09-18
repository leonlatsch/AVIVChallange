package dev.leonlatsch.avivchallange.core.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: Throwable? = null) : Result<Nothing>()
}