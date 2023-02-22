package com.txwstudio.gcard.repository

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.data.SearchResult
import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GitHubRepository {
    fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoResponse>>
}

class GitHubRepositoryImpl(private val githubRemoteDataSource: GitHubRemoteDataSource) :
    GitHubRepository {

    override fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoResponse>> = flow {
        logI("Hello World!")
        githubRemoteDataSource.searchRepo(keyword).collect {
            logI("${it.totalCount}")
        }

        emit(SearchResult.Success(SearchRepoResponse()))
    }.flowOn(Dispatchers.IO)
}