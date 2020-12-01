package com.karabas.findrepoapp.network

import android.util.Log
import com.karabas.findrepoapp.model.GetRepositoryResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers


class GitHubRepositoryImpl(private val gitHubService: GitHubService) : GitHubRepository {

    companion object {
        private const val TAG = "GitHubRepositoryImpl"
    }


    override fun findRepoByName(searchQuery: String) {
        val reposObservable = gitHubService.findRepoByName(
            searchQuery,
            "stars",
            "desc",
            15
        )
        reposObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { Log.e(TAG, "error", it) },
                onNext = {
                    handleResponse(it)
                }
            )
    }

    private fun handleResponse(response: GetRepositoryResponse) {
        response.items.forEach { item ->
            Log.d(TAG, item.toString())
        }
    }
}