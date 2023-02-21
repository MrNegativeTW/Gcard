package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.network.GitHubApi
import com.txwstudio.gcard.utils.logE
import com.txwstudio.gcard.utils.logI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRemoteDataSource {

    fun searchRepo(keyword: String): String {
        logI("Searching for $keyword")
        GitHubApi.gitHubApiService.searchRepositories(keyword)
            .enqueue(object : Callback<SearchRepoResponse> {
                override fun onResponse(
                    call: Call<SearchRepoResponse>,
                    response: Response<SearchRepoResponse>
                ) {
                    logI("${response.code()}")
                    response.body()?.let {
                        logI("totalCount: ${it.totalCount}, $it")
                    }
                }

                override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
                    logE("${t.cause}\n${t.message}")
                }
            })
        return ""
    }

}