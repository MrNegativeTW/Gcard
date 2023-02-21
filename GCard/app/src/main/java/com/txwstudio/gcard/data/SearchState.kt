package com.txwstudio.gcard.data

sealed class SearchState {
    data class Success(val searchRepoResponse: SearchRepoResponse) : SearchState()
    data class Loading(val isLoading: Boolean) : SearchState()
    data class Error(val errorMessage: String) : SearchState()
}
