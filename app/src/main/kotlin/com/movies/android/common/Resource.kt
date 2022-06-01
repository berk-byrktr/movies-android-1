package com.movies.android.common

import androidx.annotation.NonNull
import com.movies.android.common.ui.extension.then

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> errorOrLoadingMoreError(isFirst: Boolean, throwable: Throwable): Resource<T> =
            isFirst then error(throwable) ?: loadingMoreError(throwable)

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = throwable)
        }

        fun <T> loadingMoreError(throwable: Throwable): Resource<T> {
            return Resource(status = Status.LOADING_MORE_ERROR, data = null, error = throwable)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)

        fun <T> content(): Resource<T> = Resource(Status.CONTENT, null)
    }
}