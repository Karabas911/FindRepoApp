package com.karabas.findrepoapp

import androidx.lifecycle.ViewModel
import com.karabas.findrepoapp.network.GitHubRepository
import com.karabas.findrepoapp.network.GitHubRepositoryImpl

class ListViewModel : ViewModel() {

    init {
        val repo: GitHubRepository = GitHubRepositoryImpl()
        repo.findRepoByName("Hello")
    }
}