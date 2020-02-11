package com.example.squarerepos.ui.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.squarerepos.domain.UseCaseResult
import com.example.squarerepos.domain.repos.FetchReposResult
import com.example.squarerepos.domain.repos.FetchReposUseCase
import javax.inject.Inject


class ReposViewModel @Inject constructor(
    private val fetchReposUseCase: FetchReposUseCase
) : ViewModel() {

    val reposFetchResult = MutableLiveData<UseCaseResult<FetchReposResult>>()

    init {
        fetchRepos()
    }

    fun fetchRepos() {
        fetchReposUseCase.execute(null) {
            reposFetchResult.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()

        fetchReposUseCase.dispose()
    }

}