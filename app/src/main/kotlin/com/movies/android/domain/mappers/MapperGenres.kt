package com.movies.android.domain.mappers

import com.movies.android.common.Mapper
import com.movies.android.data.responses.ResGenre
import com.movies.android.ui.models.Genre
import javax.inject.Inject

class MapperGenres @Inject constructor() :
    Mapper<List<ResGenre>?, List<Genre>> {

    override fun mapFrom(type: List<ResGenre>?): List<Genre> {
        return type?.map { resGenre ->
            MapperGenre().mapFrom(resGenre)
        } ?: mutableListOf()
    }
}