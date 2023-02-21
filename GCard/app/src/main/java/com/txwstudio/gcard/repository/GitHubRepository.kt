package com.txwstudio.gcard.repository

import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.utils.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class GitHubRepository(private val githubRemoteDataSource: GitHubRemoteDataSource) {

    fun searchRepositories(keyword: String): String {
        githubRemoteDataSource.searchRepo(keyword)

        return ""
    }

}