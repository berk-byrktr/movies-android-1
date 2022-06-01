package com.movies.android.domain.interactors

import com.movies.android.common.Resource
import com.movies.android.data.repositories.RepositoryMovie
import com.movies.android.data.requests.Req
import com.movies.android.domain.mappers.MapperMovies
import com.movies.android.ui.models.Movie
import io.reactivex.Observable
import javax.inject.Inject

class UseCasePopularMovies @Inject constructor(
    private val repository: RepositoryMovie,
    private val mapper: MapperMovies
) {

    fun reqPopularMovies(req: Req): Observable<Resource<List<Movie>>> {
        return repository.reqPopularMovies(req).map { resource ->
            Resource(
                status = resource.status,
                data = resource.data?.let { mapper.mapFrom(it.results) },
                error = resource.error
            )
        }
    }
}