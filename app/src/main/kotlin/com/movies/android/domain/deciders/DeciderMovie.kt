package com.movies.android.domain.deciders

import com.movies.android.R
import com.movies.android.data.responses.ResMovie
import javax.inject.Inject

class DeciderMovie @Inject constructor() {

    fun provideImageUrl(resMovie: ResMovie?) =
        resMovie?.poster_path?.let {
            return@let "https://image.tmdb.org/t/p/w500" + resMovie.poster_path
        } ?: ""

    fun provideDuration() = "-"

    fun provideProgress(resMovie: ResMovie?) = (resMovie?.vote_average?.times(10) ?: 0).toInt()

    fun provideProgressColor(resMovie: ResMovie?): Int {
        val rate = (resMovie?.vote_average?.times(10) ?: 0).toInt()
        return if (rate >= 50) R.color.green else R.color.yellow
    }
}