package com.movies.android.ui.viewstates

import com.movies.android.common.Status
import com.movies.android.ui.customs.CustomPage

class ViewStatePage<T>(
    val status: Status,
    val error: Throwable? = null,
    val data: T? = null
) {

    fun isSuccess() = status == Status.SUCCESS

    fun isLoading() = status == Status.LOADING

    fun isError() = status == Status.ERROR || status == Status.LOADING_MORE_ERROR

    fun isLoadingMoreError() = status == Status.LOADING_MORE_ERROR

    fun getState() =
        when (status) {
            Status.LOADING -> CustomPage.State.LOADING
            Status.SUCCESS -> CustomPage.State.CONTENT
            Status.ERROR -> CustomPage.State.ERROR
            Status.CONTENT -> CustomPage.State.CONTENT
            Status.LOADING_MORE_ERROR -> CustomPage.State.CONTENT
        }
}