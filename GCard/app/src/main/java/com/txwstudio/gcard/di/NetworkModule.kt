package com.txwstudio.gcard.di

import com.txwstudio.gcard.network.GithubApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL_GITHUB_API = "https://api.github.com"

val networkModule = module {
    single { provideHttpClient() }
    single { provideRetrofitClient(get()) }
    single { provideGitHubApiService(get()) }
}

private fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
    }.build()
}

private fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_GITHUB_API)
        .build()
}

private fun provideGitHubApiService(retrofit: Retrofit): GithubApiService =
    retrofit.create(GithubApiService::class.java)
