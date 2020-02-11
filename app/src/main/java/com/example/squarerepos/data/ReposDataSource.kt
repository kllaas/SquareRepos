package com.example.squarerepos.data

import com.example.squarerepos.data.model.Repository
import io.reactivex.Observable

interface ReposDataSource {

    fun getRepos(): Observable<List<Repository>>

}