package com.karabas.findrepoapp.network

import com.karabas.findrepoapp.model.GetRepositoryResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun findRepoByName(
        @Query("q") searchQuery: String,
        @Query("sort") sortType: String,
        @Query("order") order: String,
        @Query("per_page") itemCount: Int
    ): Observable<GetRepositoryResponse>


}