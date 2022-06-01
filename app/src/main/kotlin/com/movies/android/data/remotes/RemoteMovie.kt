package com.movies.android.data.remotes

import com.movies.android.data.apis.ApiMovie
import com.movies.android.data.requests.Req
import com.movies.android.data.responses.ResMovies
import io.reactivex.Observable
import javax.inject.Inject

class RemoteMovie @Inject constructor(private val api: ApiMovie) {

    fun reqPopularMovies(req: Req) = api.reqPopularMovies(req.language, req.page)
    fun reqMovie(req: Req) = api.reqMovie(req.id)

    fun reqMovies(req1: Req, req2: Req) = Observable.zip(
        api.reqCurrentMovies(req1.language, req1.page),
        api.reqPopularMovies(req2.language, req2.page)
    ) { res1, res2 ->
        ResMovies(res1.results, res2.results)
    }
}