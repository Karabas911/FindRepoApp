package com.karabas.findrepoapp


data class Resource<out T>(
    val status: Status,
    val data: T?,
    val msg: String?,
    val errorReason: Int
) {

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null, 0)
        }

        fun <T> error(errorReason: Int = 0, msg: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg, errorReason)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null, 0)
        }
    }
}