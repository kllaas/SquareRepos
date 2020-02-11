package com.example.squarerepos.domain


sealed class UseCaseResult<out R> {

    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Error(val exception: Throwable) : UseCaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}