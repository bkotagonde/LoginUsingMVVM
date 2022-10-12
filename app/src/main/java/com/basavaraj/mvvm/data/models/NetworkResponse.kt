package com.basavaraj.mvvm.data.models

sealed class NetworkResponse<out T>
{
    data class Success<out T>(val data: T? = null) : NetworkResponse<T>()
    data class Error<out T>(val error: String) : NetworkResponse<T>()
    data class Loading(val nothing: Nothing?=null) : NetworkResponse<Nothing>()
}