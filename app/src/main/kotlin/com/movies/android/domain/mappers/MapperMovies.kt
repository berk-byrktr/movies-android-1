package com.movies.android.domain.mappers

import com.movies.android.common.Mapper
import com.movies.android.data.responses.ResMovie
import com.movies.android.domain.deciders.DeciderMovie
import com.movies.android.ui.models.Movie
import javax.inject.Inject

class MapperMovies @Inject constructor(private val decider: DeciderMovie) :
    Mapper<List<ResMovie>?, List<Movie>> {

    override fun mapFrom(type: List<ResMovie>?): List<Movie> {
        return type?.map { resMovie ->
            MapperMovie(decider).mapFrom(resMovie)
        } ?: mutableListOf()
    }
}