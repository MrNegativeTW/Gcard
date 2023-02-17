package com.txwstudio.gcard.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GitHubApi {

    private const val BASE_URL_GITHUB_API = "https://api.github.com"

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_GITHUB_API)
        .build()

    val gitHubApiService: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}