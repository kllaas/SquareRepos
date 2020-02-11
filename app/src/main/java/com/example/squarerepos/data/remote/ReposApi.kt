package com.example.squarerepos.data.remote

import com.example.squarerepos.data.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET

interface ReposApi {

    @GET(GET_REPOS_URL)
    fun getRepos(): Observable<List<Repository>>

    companion object {
        private const val GET_REPOS_URL = "orgs/square/repos"
    }

}