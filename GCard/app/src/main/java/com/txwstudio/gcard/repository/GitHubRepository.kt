package com.txwstudio.gcard.repository

import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.utils.logI

class GitHubRepository(private val githubRemoteDataSource: GitHubRemoteDataSource) {

    fun getRepository(): String {
        logI("Hello World!")
        githubRemoteDataSource.getRepo()
        return ""
    }

}