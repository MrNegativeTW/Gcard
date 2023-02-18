package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.network.GitHubApi
import com.txwstudio.gcard.utils.logE
import com.txwstudio.gcard.utils.logI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRemoteDataSource {

    fun searchRepo(): String {
        logI("Hello World!")
        GitHubApi.gitHubApiService.searchRepositories("timetable")
            .enqueue(object : Callback<SearchRepoResponse> {
                override fun onResponse(
                    call: Call<SearchRepoResponse>,
                    response: Response<SearchRepoResponse>
                ) {
                    response.body()?.let {
                        logI("${it.totalCount}, ${it.items[0].name}")
                    }
                }

                override fun onFailure(call: Call<SearchRepoResponse>, t: Throwable) {
                    logE("${t.cause}\n${t.message}")
                }
            })
        return ""
    }

}