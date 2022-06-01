package com.movies.android.data.repositories

import com.movies.android.common.Resource
import com.movies.android.common.ui.extension.applyContent
import com.movies.android.common.ui.extension.applyLoading
import com.movies.android.data.remotes.RemoteMovie
import com.movies.android.data.requests.Req
import com.movies.android.data.responses.Res
import com.movies.android.data.responses.ResMovie
import com.movies.android.data.responses.ResMovies
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryMovie @Inject constructor(private val remote: RemoteMovie) {

    fun reqPopularMovies(req: Req): Observable<Resource<Res<List<ResMovie>>>> = remote
        .reqPopularMovies(req)
        .map { Resource.success(it) }
        .onErrorReturn { Resource.errorOrLoadingMoreError(req.page == 1, it) }
        .subscribeOn(Schedulers.io())
        .compose(applyContent())

    fun reqMovie(req: Req): Observable<Resource<ResMovie>> = remote
        .reqMovie(req)
        .map { Resource.success(it) }
        .onErrorReturn { Resource.error(it) }
        .subscribeOn(Schedulers.io())
        .compose(applyLoading())

    fun reqMovies(req1: Req, req2: Req): Observable<Resource<ResMovies>> = remote
        .reqMovies(req1, req2)
        .map { Resource.success(it) }
        .onErrorReturn { Resource.error(it) }
        .subscribeOn(Schedulers.io())
        .compose(applyLoading())
}