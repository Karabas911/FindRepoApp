package com.karabas.findrepoapp.di

import com.karabas.findrepoapp.di.NetworkModule.networkModule
import com.karabas.findrepoapp.di.RepoListModule.repoListModule

object AppModules {

    val appModules = arrayListOf(
        networkModule,
        repoListModule
    )
}