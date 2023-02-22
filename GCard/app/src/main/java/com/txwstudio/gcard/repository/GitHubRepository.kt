package com.txwstudio.gcard.repository

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.data.SearchResult
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoResponse>>
}