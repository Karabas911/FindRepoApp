package com.karabas.findrepoapp.model


import com.google.gson.annotations.SerializedName

data class GetRepositoryResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepositoryRemote>,
    @SerializedName("total_count")
    val totalCount: Int
)