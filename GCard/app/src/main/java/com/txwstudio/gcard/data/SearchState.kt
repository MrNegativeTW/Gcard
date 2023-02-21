package com.txwstudio.gcard.data

sealed class SearchState {
    data class Success(val data: String) : SearchState()
    data class Loading(val isLoading: Boolean) : SearchState()
    data class Error(val errorMessage: String) : SearchState()
}
