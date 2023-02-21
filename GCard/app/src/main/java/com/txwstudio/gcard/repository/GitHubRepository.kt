package com.txwstudio.gcard.repository

import com.txwstudio.gcard.data.SearchRepoResponse
import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.flow.Flow

class GitHubRepository(private val githubRemoteDataSource: GitHubRemoteDataSource) {

    fun searchRepo(keyword: String): Flow<SearchRepoResponse> {
        val a = githubRemoteDataSource.searchRepo(keyword)
        logI("${a.totalCount}")

        return SearchRepoResponse()
    }

}