package com.movies.android.data.responses

data class ResMovies(
    val resCurrentMovies: List<ResMovie>,
    val resPopularMovies: List<ResMovie>,
)