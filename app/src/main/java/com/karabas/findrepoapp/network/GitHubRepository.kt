package com.karabas.findrepoapp.network

interface GitHubRepository {

    fun findRepoByName(searchQuery: String)

}