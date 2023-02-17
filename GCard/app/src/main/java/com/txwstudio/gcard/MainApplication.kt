package com.txwstudio.gcard

import android.app.Application
import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.repository.GitHubRepository
import com.txwstudio.gcard.utils.logI

class MainApplication : Application() {

    val githubRepository by lazy { GitHubRepository(GitHubRemoteDataSource()) }

    override fun onCreate() {
        super.onCreate()
        logI("Hello Hell!")
    }
}