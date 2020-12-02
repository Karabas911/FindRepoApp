package com.karabas.findrepoapp.network

import com.karabas.findrepoapp.model.GetRepositoryResponse
import io.reactivex.rxjava3.core.Observable


class GitHubRepositoryImpl(private val gitHubService: GitHubService) : GitHubRepository {

    companion object {
        private const val TAG = "GitHubRepositoryImpl"
        private const val REQUEST_QUERY_SORT_TYPE = "stars"
        private const val REQUEST_QUERY_ORDER = "desc"
        private const val REQUEST_QUERY_COUNT = 15
    }

    override fun findRepoByName(searchQuery: String, pageNumber: Int): Observable<GetRepositoryResponse> {
        return gitHubService.findRepoByName(
            searchQuery,
            REQUEST_QUERY_SORT_TYPE,
            REQUEST_QUERY_ORDER,
            REQUEST_QUERY_COUNT,
            pageNumber
        )
    }
}