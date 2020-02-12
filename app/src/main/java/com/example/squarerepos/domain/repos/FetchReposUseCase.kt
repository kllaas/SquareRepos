package com.example.squarerepos.domain.repos

import com.example.squarerepos.data.ReposRepository
import com.example.squarerepos.domain.UseCase
import com.example.squarerepos.domain.UseCaseResult
import com.example.squarerepos.utils.ext.applySchedulers
import javax.inject.Inject

class FetchReposUseCase @Inject constructor(
    private val reposRepository: ReposRepository,
    private val repositoryToViewMapper: RepositoryToViewMapper
) : UseCase<Any?, FetchReposResult>() {

    override fun execute(
        params: Any?,
        onExecuted: (UseCaseResult<FetchReposResult>) -> Unit
    ) {
        reposRepository.getRepos()
            .map {repositoryToViewMapper.map(it)}
            .applySchedulers()
            .subscribe(

                {
                    onExecuted(UseCaseResult.Success(FetchReposResult(it)))
                },{
                    onExecuted(UseCaseResult.Error(it))
                }

            ).addToDisposables()
    }

}

data class FetchReposResult(
    val repos: List<RepositoryView>
)