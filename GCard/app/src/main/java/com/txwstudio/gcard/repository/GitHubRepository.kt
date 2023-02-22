package com.txwstudio.gcard.repository

import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.data.SearchResult
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoApiModel>>
}