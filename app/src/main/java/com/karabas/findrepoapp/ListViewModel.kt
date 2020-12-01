package com.karabas.findrepoapp

import androidx.lifecycle.ViewModel
import com.karabas.findrepoapp.network.GitHubRepository
import com.karabas.findrepoapp.network.GitHubRepositoryImpl

class ListViewModel(private val repo: GitHubRepository) : ViewModel() {

    init {
        repo.findRepoByName("Hello")
    }
}