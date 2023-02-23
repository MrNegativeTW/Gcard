package com.txwstudio.gcard.repository

import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.data.SearchResult
import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GitHubRepositoryImpl(
    private val githubRemoteDataSource: GitHubRemoteDataSource
) : GitHubRepository {

    override suspend fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoApiModel>> =
        flow {
            emit(SearchResult.Loading)
            githubRemoteDataSource.searchRepo(keyword).collect {
                when (it) {
                    is SearchResult.Success -> {
                        // TODO("Save result to local database")
                        emit(it)
                    }

                    is SearchResult.Error -> {
                        // TODO("Search in LocalDataSource")
                        emit(it)
                    }

                    is SearchResult.Loading -> {
                        emit(it)
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GitHubRepositoryImpl"
    }
}