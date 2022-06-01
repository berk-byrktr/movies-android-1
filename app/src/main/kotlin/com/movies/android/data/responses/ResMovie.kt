package com.movies.android.data.responses

data class ResMovie(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Int?,
    val genres: List<ResGenre>?
)