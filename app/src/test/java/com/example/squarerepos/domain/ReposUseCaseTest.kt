package com.example.squarerepos.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.example.squarerepos.data.ReposRepository
import com.example.squarerepos.domain.repos.FetchReposUseCase
import com.example.squarerepos.domain.repos.RepositoryToViewMapper
import com.example.squarerepos.repository.ReposTestData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldEqual
import org.junit.*
import org.junit.rules.TestRule



class ReposUseCaseTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    companion object {

        @JvmStatic
        @BeforeClass
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }

    }

    @Before
    fun setUp() {

    }

    @Test
    fun `Given list of the repos, when execute(), should return mapped to view list`() {
        val lifecycle = LifecycleRegistry(mock())
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        val repository = mock<ReposRepository> {
            on { getRepos() }.doReturn(Observable.just(ReposTestData.repositories))
        }

        val mapper = RepositoryToViewMapper()

        FetchReposUseCase(repository, mapper).apply {
            execute(null) { result ->
                (result as UseCaseResult.Success).data.repos shouldEqual ReposTestData.repositoriesView
            }
        }
    }

    @Test
    fun `Given some error in repository, when execute(), should return wrapped result`() {
        val lifecycle = LifecycleRegistry(mock())
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        val e = TestException()

        val repository = mock<ReposRepository> {
            on { getRepos() }.thenAnswer { throw e }
        }

        val mapper = RepositoryToViewMapper()

        FetchReposUseCase(repository, mapper).apply {
            execute(null) { result ->
                (result as UseCaseResult.Error).exception shouldEqual e
            }
        }
    }

    @After
    fun tearDown() {

    }
}


private class TestException : Exception()