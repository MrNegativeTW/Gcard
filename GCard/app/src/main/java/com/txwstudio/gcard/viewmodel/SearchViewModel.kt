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

    private val _searchState = MutableLiveData<SearchResult<SearchRepoApiModel>>()
    val searchState: LiveData<SearchResult<SearchRepoApiModel>>
        get() = _searchState

    private lateinit var searchJob: Job

    fun submitSearchKeyword(keyword: String) {
        if (this::searchJob.isInitialized && searchJob.isActive) searchJob.cancel()

        // Sterilize keyword
        val trimmedKeyword = keyword.trim()
        if (trimmedKeyword.isEmpty()) {
            _searchState.value = SearchResult.Clear
            return
        }

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            logI("Searching for: \"$keyword\"")
            gitHubRepository.searchRepo(keyword)
                .cancellable()
                .flowOn(Dispatchers.IO)
                .catch {
                    // TODO("Maybe throw something from repository?")
                }
                .collect {
                    withContext(Dispatchers.Main) { _searchState.value = it }
                }
        }
    }

    /**
     * 儲存上次按下搜尋時的字詞到 Room
     */
    fun saveSearchKeyword(keyword: String) {
        logI("saveSearchKeyword()")
    }
}