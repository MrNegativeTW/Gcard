package com.txwstudio.gcard

import android.app.Application
import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.di.networkModule
import com.txwstudio.gcard.repository.GitHubRepository
import com.txwstudio.gcard.repository.GitHubRepositoryImpl
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import com.txwstudio.gcard.viewmodel.SearchViewModel

class GcardApplication : Application() {

    private val myAppModules = module {
        single { GitHubRemoteDataSource(get()) }
        single<GitHubRepository> { GitHubRepositoryImpl(get()) }

        viewModelOf(::SearchViewModel)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            modules(listOf(networkModule, myAppModules))
        }
    }
}