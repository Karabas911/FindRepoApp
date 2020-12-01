package com.karabas.findrepoapp.di

import com.karabas.findrepoapp.ListViewModel
import com.karabas.findrepoapp.network.GitHubRepository
import com.karabas.findrepoapp.network.GitHubRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RepoListModule {

    val repoListModule = module {
        single<GitHubRepository> { GitHubRepositoryImpl(gitHubService = get()) }

        viewModel { ListViewModel(repo = get()) }
    }
}