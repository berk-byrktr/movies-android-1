package com.movies.android.ui.models

import com.movies.android.R

data class Movie(
    val id: String = "",
    val imageUrl: String = "",
    val title: String = "",
    val releaseDate: String = "",
    val duration: String = "",
    val overview: String = "",
    val genres: List<Genre> = mutableListOf(),
    val progress: Int = 0,
    val progressColor: Int = R.color.white
)