package com.example.squarerepos.data.remote

import com.example.squarerepos.data.ReposDataSource
import com.example.squarerepos.data.model.Repository
import io.reactivex.Observable
import javax.inject.Inject

class ReposRemoteDataSource @Inject constructor(
    private val api: ReposApi
) : ReposDataSource {

    override fun getRepos(): Observable<List<Repository>> {
        return api.getRepos()
    }

}