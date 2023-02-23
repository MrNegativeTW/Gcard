package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.data.SearchResult
import com.txwstudio.gcard.network.GithubApiService
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.lang.Exception

class GitHubRemoteDataSource(private val githubApiService: GithubApiService) {

    fun searchRepo(keyword: String): Flow<SearchResult<SearchRepoApiModel>> = flow {
        logI(TAG, "Hello World!")
        emit(SearchResult.Loading)
        val response = githubApiService.searchRepositories(keyword)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(SearchResult.Success(it))
            }
        } else {
            logI(TAG, response.errorBody().toString())
            emit(SearchResult.Error(Exception()))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GitHubRemoteDataSource"
    }
}