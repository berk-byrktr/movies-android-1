package com.movies.android.domain.interactors

import com.movies.android.common.Resource
import com.movies.android.data.repositories.RepositoryMovie
import com.movies.android.data.requests.Req
import com.movies.android.domain.mappers.MapperMovie
import com.movies.android.ui.models.Movie
import io.reactivex.Observable
import javax.inject.Inject

class UseCaseMovie @Inject constructor(
    private val repository: RepositoryMovie,
    private val mapper: MapperMovie
) {

    fun reqMovie(req: Req): Observable<Resource<Movie>> {
        return repository.reqMovie(req).map { resource ->
            Resource(
                status = resource.status,
                data = resource.data?.let { mapper.mapFrom(it) },
                error = resource.error
            )
        }
    }
}