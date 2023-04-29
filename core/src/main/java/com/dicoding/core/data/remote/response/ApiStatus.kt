package com.dicoding.core.data.remote.response

sealed class ApiStatus<out T> {
   data class Success<out T>(val data: T) : ApiStatus<T>()
   data class Error(val error: String) : ApiStatus<Nothing>()
   object Empty : ApiStatus<Nothing>()
}