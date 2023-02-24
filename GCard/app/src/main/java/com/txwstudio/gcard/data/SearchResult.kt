package com.txwstudio.gcard.data

import kotlin.Exception

sealed class SearchResult<out T> {
    data class Success<out T>(val data: T) : SearchResult<T>()
    data class Error(val messages: String) : SearchResult<Nothing>()
    object Loading : SearchResult<Nothing>()
    object Clear : SearchResult<Nothing>()
}
