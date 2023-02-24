package com.txwstudio.gcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.data.SearchResult
import com.txwstudio.gcard.repository.GitHubRepository
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {

    // TODO("Placeholder")
    private val _uiState = MutableLiveData<SearchResult<SearchRepoApiModel>>()
    val uiState: LiveData<SearchResult<SearchRepoApiModel>>
        get() = _uiState

    private lateinit var searchJob: Job

    fun submitSearchKeyword(keyword: String) {
        val trimmedKeyword = keyword.trim()
        if (trimmedKeyword.isEmpty()) return
        logI("Searching for: \"$keyword\"")
        if (this::searchJob.isInitialized && searchJob.isActive) searchJob.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.searchRepo(keyword)
                .cancellable()
                .flowOn(Dispatchers.IO)
                .catch {
                    // TODO("Maybe throw something from repository?")
                }
                .collect {
                    withContext(Dispatchers.Main) { _uiState.value = it }
                }
        }
    }
}