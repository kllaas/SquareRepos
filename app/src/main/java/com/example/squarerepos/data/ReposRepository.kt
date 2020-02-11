package com.example.squarerepos.data

import com.example.squarerepos.data.model.Repository
import com.example.squarerepos.di.app.ApplicationModule.Companion.REPOS_LOCAL_DATA_SOURCE
import com.example.squarerepos.di.app.ApplicationModule.Companion.REPOS_REMOTE_DATA_SOURCE
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * ReposRepository - sounds weird but I don't know how to make it better ¯\_(ツ)_/¯
*/
@Singleton
open class ReposRepository @Inject constructor(
    @Named(REPOS_REMOTE_DATA_SOURCE) private val remoteDataSource: ReposDataSource,
    @Named(REPOS_LOCAL_DATA_SOURCE) private val localDataSource: ReposDataSource
) : ReposDataSource {

    override fun getRepos(): Observable<List<Repository>> {
        return remoteDataSource.getRepos()
    }

    private fun getReposFromLocalStore(): Observable<List<Repository>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}