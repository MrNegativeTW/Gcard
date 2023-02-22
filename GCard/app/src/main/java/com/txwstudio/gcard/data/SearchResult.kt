package com.txwstudio.gcard.data

import java.lang.Exception

sealed class SearchResult<out T> {
    data class Success<out T>(val data: T) : SearchResult<T>()
    data class Error(val exception: Exception) : SearchResult<Nothing>()
}
