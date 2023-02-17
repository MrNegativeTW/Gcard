package com.txwstudio.gcard.data

sealed class Result {
    data class Success(val data: String)
    data class Error(val exception: Exception)
}
