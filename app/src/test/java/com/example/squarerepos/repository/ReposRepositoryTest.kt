package com.example.squarerepos.repository

import com.example.squarerepos.data.ReposRepository
import com.example.squarerepos.data.local.ReposLocalDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test

class ReposRepositoryTest {
    lateinit var reposRepository: ReposRepository

    @Before
    fun setUp() {
        reposRepository = ReposRepository(
            remoteDataSource = mock {
                on { getRepos() }.doReturn(Observable.just(ReposTestData.repositories))
            },

            localDataSource = ReposLocalDataSource()
        )
    }

    @Test
    fun `Given nice internet connection, when getRepos, should return list of repos from remoteDataSource`() {
        reposRepository.getRepos()
            .test()
            .assertNoErrors()
            .assertValue(ReposTestData.repositories)
    }

    @Test
    fun `Given bad internet connection, when getRepos, should return list of repos from localDataSource`() {

    }

    @After
    fun tearDown() {

    }
}