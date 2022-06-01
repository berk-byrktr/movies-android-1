package com.movies.android.domain.mappers

import com.movies.android.common.Mapper
import com.movies.android.data.responses.ResMovie
import com.movies.android.domain.deciders.DeciderMovie
import com.movies.android.ui.models.Movie
import javax.inject.Inject

class MapperMovie @Inject constructor(private val decider: DeciderMovie) :
    Mapper<ResMovie?, Movie> {

    override fun mapFrom(type: ResMovie?): Movie {
        return Movie(
            id = type?.id.orEmpty(),
            imageUrl = decider.provideImageUrl(type),
            title = type?.title.orEmpty(),
            releaseDate = type?.release_date.orEmpty(),
            duration = decider.provideDuration(),
            overview = type?.overview.orEmpty(),
            genres = MapperGenres().mapFrom(type?.genres),
            progress = decider.provideProgress(type),
            progressColor = decider.provideProgressColor(type)
        )
    }
}