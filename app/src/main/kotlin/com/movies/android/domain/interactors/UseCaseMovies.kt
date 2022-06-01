package com.movies.android.domain.interactors

import com.movies.android.common.Resource
import com.movies.android.data.repositories.RepositoryMovie
import com.movies.android.data.requests.Req
import com.movies.android.domain.mappers.MapperMovies
import com.movies.android.ui.models.Movies
import io.reactivex.Observable
import javax.inject.Inject

class UseCaseMovies @Inject constructor(
    private val repository: RepositoryMovie,
    private val mapper: MapperMovies
) {

    fun reqMovies(req1: Req, req2: Req): Observable<Resource<Movies>> {
        return repository.reqMovies(req1, req2).map { resource ->
            Resource(
                status = resource.status,
                data = resource.data?.let {
                    Movies(
                        currentMovies = mapper.mapFrom(it.resCurrentMovies),
                        popularMovies = mapper.mapFrom(it.resPopularMovies)
                    )
                },
                error = resource.error
            )
        }
    }
}