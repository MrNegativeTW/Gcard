package com.txwstudio.gcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.txwstudio.gcard.MainApplication
import com.txwstudio.gcard.data.SearchState
import com.txwstudio.gcard.repository.GitHubRepository
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SearchViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {

    private val _uiState = MutableLiveData<SearchState>()
    val uiState: LiveData<SearchState> = _uiState

    init {

    }

    fun submitSearchKeyword(keyword: String) {
        logI("Keyword received: \"$keyword\"")
        if (keyword.trim().isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.searchRepositories(keyword)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the Application object from extras
                val application = checkNotNull(this[APPLICATION_KEY])

                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = createSavedStateHandle()

                SearchViewModel((application as MainApplication).githubRepository)
            }
        }
    }
}