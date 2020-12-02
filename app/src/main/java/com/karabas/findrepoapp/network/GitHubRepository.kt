package com.karabas.findrepoapp.network

import com.karabas.findrepoapp.model.GetRepositoryResponse
import io.reactivex.rxjava3.core.Observable

interface GitHubRepository {

    fun findRepoByName(searchQuery: String, pageNumber: Int): Observable<GetRepositoryResponse>

}