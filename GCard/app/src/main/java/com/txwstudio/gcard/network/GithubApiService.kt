package com.txwstudio.gcard.network

import com.txwstudio.gcard.data.SearchRepoResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface GithubApiService {

    @GET("/search/repositories")
    fun searchRepositories(@Field("some") some: String): Call<SearchRepoResponse>
}