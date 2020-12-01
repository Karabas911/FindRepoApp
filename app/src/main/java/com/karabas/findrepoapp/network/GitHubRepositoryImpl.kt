package com.karabas.findrepoapp.network

import android.util.Log
import com.karabas.findrepoapp.Constants
import com.karabas.findrepoapp.model.GetRepositoryResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GitHubRepositoryImpl : GitHubRepository {

    companion object {
        private const val TAG = "GitHubRepositoryImpl"
    }

    private val gitHubService: GitHubService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        gitHubService = retrofit.create(GitHubService::class.java)
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