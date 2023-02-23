package com.txwstudio.gcard.network

import com.txwstudio.gcard.data.SearchRepoApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    /**
     * Search repositories on GitHub
     *
     * @param q Required. Keyword want to search
     * @param sort Default: "bestmatch", can be one of: "stars", "forks", "help-wanted-issues", "updated"
     * @param order Require param [sort]. Default: "desc", can be one of "desc" or "asc"
     * @param perPage The number of results per page (max 100). Default: 30
     * @param page Page number of the results to fetch. Default: 1
     */
    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String = "bestmatch",
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): Response<SearchRepoApiModel>
}