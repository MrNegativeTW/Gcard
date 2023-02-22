package com.txwstudio.gcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.txwstudio.gcard.repository.GitHubRepository
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {

    // TODO("Placeholder")
    private val _uiState = MutableLiveData<Boolean>()
    val uiState: LiveData<Boolean>
        get() = _uiState

    init {

    }

    fun submitSearchKeyword(keyword: String) {
        val trimmedKeyword = keyword.trim()
        if (trimmedKeyword.isEmpty()) return
        logI("Searching for: \"$keyword\"")
        viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.searchRepo(keyword)
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect {

                }
        }
    }
}