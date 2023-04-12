package com.example.nutri.core

sealed class ResultState<out R>{
    data class Success<out T>(val value: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}
val <T> ResultState<T>.data : T?
    get() = (this as ResultState.Success).data
