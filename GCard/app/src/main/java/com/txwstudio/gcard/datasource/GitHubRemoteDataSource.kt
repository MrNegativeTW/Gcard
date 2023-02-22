package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.network.GitHubApi
import com.txwstudio.gcard.utils.logE
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRemoteDataSource {

    fun searchRepo(keyword: String): Flow<SearchRepoResponse> = flow {
        logI("Hello World!")
        val searchRepoResponse = GitHubApi.gitHubApiService.searchRepositories(keyword)
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
        emit(SearchRepoResponse())
    }.flowOn(Dispatchers.IO)

}