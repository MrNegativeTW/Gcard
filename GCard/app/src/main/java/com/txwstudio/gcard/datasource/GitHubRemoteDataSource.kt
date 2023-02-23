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
        logI(TAG, "Searching \"$keyword\" from remote data source")
        val response = githubApiService.searchRepositories(keyword)
        val responseCode = response.code()
        if (response.isSuccessful) {
            // Status code between 200..299
            response.body()?.let {
                logI(TAG, "現在發射 $keyword, totalCount: ${it.totalCount}")
                emit(SearchResult.Success(it))
            }
        } else if (responseCode == 403) {
            // Rate limit exceeded
            emit(SearchResult.Error("[403] Rate limit exceeded"))
        } else {
            logI(TAG, response.errorBody().toString())
            emit(SearchResult.Error("Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GitHubRemoteDataSource"
    }
}