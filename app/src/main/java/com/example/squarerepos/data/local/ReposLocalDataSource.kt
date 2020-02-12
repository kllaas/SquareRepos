package com.example.squarerepos.data.local

import com.example.squarerepos.data.ReposDataSource
import com.example.squarerepos.data.model.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

@Named("reposLocalDataSource")
class ReposLocalDataSource @Inject constructor(

) : ReposDataSource {

    override fun getRepos(): Observable<List<Repository>> {
        return Observable.just(emptyList())
    }

}