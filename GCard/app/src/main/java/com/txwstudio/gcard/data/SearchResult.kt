package com.txwstudio.gcard.data

import kotlin.Exception

sealed class SearchResult<out T> {
    data class Success<out T>(val data: T) : SearchResult<T>()
    data class Error(val exception: Exception) : SearchResult<Nothing>()
    object Loading : SearchResult<Nothing>()
}
