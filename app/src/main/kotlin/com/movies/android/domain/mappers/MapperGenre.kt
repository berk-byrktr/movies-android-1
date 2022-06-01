package com.movies.android.domain.mappers

import com.movies.android.common.Mapper
import com.movies.android.data.responses.ResGenre
import com.movies.android.ui.models.Genre
import javax.inject.Inject

class MapperGenre @Inject constructor() :
    Mapper<ResGenre?, Genre> {

    override fun mapFrom(type: ResGenre?): Genre {
        return Genre(name = type?.name?.toUpperCase().orEmpty())
    }
}