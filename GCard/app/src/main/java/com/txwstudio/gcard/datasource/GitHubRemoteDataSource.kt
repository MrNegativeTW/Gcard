package com.txwstudio.gcard.datasource

import com.txwstudio.gcard.network.GitHubApi
import com.txwstudio.gcard.utils.logI

class GitHubRemoteDataSource {

    fun getRepo(): String {
        logI("Hello world!")
        GitHubApi.gitHubApiService.searchRepositories("")
        return ""
    }

}