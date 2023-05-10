package com.example.nutri.core

sealed class ResultState<out R>{
    data class Success<out T>(val value: T) : ResultState<T>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
}
val <T> ResultState<T>.data : T?
    get() = (this as ResultState.Success).data
