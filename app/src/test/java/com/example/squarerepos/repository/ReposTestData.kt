package com.example.squarerepos.repository

import com.example.squarerepos.data.model.Repository


object ReposTestData {

    val repository1 = Repository(
        id = "id1",
        name = "title1",
        description = "description1")

    val repository2 = Repository(
        id = "id2",
        name = "title2",
        description = "description2")

    val repositories = listOf(repository1, repository2)

    val repositoryView1 = Repository(
        id = "id1",
        name = "title1",
        description = "description1")

    val repositoryView2 = Repository(
        id = "id2",
        name = "title2",
        description = "description2")

    val repositoriesView = listOf(repositoryView1, repositoryView2)

}