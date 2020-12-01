package com.karabas.findrepoapp

import android.app.Application
import com.karabas.findrepoapp.di.AppModules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FindRepoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@FindRepoApp)
            modules(appModules)
        }
    }
}