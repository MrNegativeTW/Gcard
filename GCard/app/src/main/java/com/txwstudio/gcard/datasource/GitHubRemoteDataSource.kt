package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.data.SearchRepoApiModel
import com.txwstudio.gcard.network.GitHubApi
import com.txwstudio.gcard.network.GithubApiService
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GitHubRemoteDataSource(private val githubApiService: GithubApiService) {

    fun searchRepo(keyword: String): Flow<SearchRepoApiModel> = flow {
        logI(TAG, "Hello World!")
        val searchRepoResponse = githubApiService.searchRepositories(keyword)
        emit(searchRepoResponse)
//            .enqueue(object : Callback<SearchRepoResponse> {
//                override fun onResponse(
//                    call: Call<SearchRepoResponse>,
//                    response: Response<SearchRepoResponse>
//                ) {
//                    logI("${response.code()}")
//                    if (response.code() == 200) {
//                        response.body()?.let {
//                            logI("totalCount: ${it.totalCount}, $it")
//                            emit(it)
//                            return@let it
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
//                    logE("${t.cause}\n${t.message}")
//                }
//            })
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GitHubRemoteDataSource"
    }
}