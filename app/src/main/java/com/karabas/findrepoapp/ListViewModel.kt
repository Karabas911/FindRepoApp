package com.karabas.findrepoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karabas.findrepoapp.model.Repository
import com.karabas.findrepoapp.network.GitHubRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel(private val repo: GitHubRepository) : ViewModel() {

    companion object {
        const val TAG = "ListViewModel"
        const val REPO_LIST_PAGE_NUMBER_1 = 1
        const val REPO_LIST_PAGE_NUMBER_2 = 2
    }

    private val repoLiveData = MutableLiveData<Resource<List<Repository>>>()

    fun getRepoLiveData(): LiveData<Resource<List<Repository>>> = repoLiveData

    fun startRepoSearch(searchQuery: String) {
        repoLiveData.value = Resource.loading()
        // Load first 15 items from page 1
        val firsResult = repo.findRepoByName(searchQuery, REPO_LIST_PAGE_NUMBER_1)
        // Load rest 15 items from page 2
        val secondResult = repo.findRepoByName(searchQuery, REPO_LIST_PAGE_NUMBER_2)

        val resultList = arrayListOf<Repository>()
        firsResult
            .mergeWith(secondResult)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val repositoryList = arrayListOf<Repository>()
                response.items.forEach { repoRemote ->
                    Log.d(TAG, "Repo name = ${repoRemote.name}, score = ${repoRemote.score}")
                    repositoryList.add(
                        Repository(
                            repoRemote.fullName,
                            repoRemote.language,
                            repoRemote.stargazersCount
                        )
                    )
                }
                repositoryList
            }
            .subscribeBy(
                onError = { throwable ->
                    Log.e(TAG, "error", throwable)
                    repoLiveData.value = Resource.error(msg = throwable.localizedMessage)
                },
                onNext = { resultList.addAll(it) },
                onComplete = { repoLiveData.value = Resource.success(resultList) }
            )
    }
}