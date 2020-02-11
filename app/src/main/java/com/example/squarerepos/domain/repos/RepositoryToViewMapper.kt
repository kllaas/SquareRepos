package com.example.squarerepos.domain.repos

import com.example.squarerepos.data.model.Repository
import javax.inject.Inject

class RepositoryToViewMapper @Inject constructor() {

    fun map (repos: List<Repository>) = repos.map {
        map(it)
    }

    fun map(repository: Repository) : RepositoryView {
        return RepositoryView(repository.id, repository.name, repository.description)
    }

}