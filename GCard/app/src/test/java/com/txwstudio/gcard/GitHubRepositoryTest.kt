package com.txwstudio.gcard

import com.txwstudio.gcard.datasource.GitHubRemoteDataSource
import com.txwstudio.gcard.repository.GitHubRepositoryImpl
import io.kotest.core.spec.style.FunSpec
import io.mockk.coEvery
import io.mockk.mockk

class GitHubRepositoryTest : FunSpec({

    lateinit var remoteDataSource: GitHubRemoteDataSource
    lateinit var repository: GitHubRepositoryImpl

    beforeTest {
        remoteDataSource = mockk()
        repository = mockk()
    }

    test("Search repositories") {
        val keyword = "tea"
        coEvery { repository.searchRepo(keyword) }

//        runBlocking {
//            repository.searchRepo(keyword).collect {
//                when (it) {
//                    else -> {
//                        println("666 ${it.toString()}")
//                    }
//                }
//            }
//        }

    }
})