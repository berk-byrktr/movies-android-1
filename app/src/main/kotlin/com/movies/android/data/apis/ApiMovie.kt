package com.movies.android.data.apis

import com.movies.android.data.responses.Res
import com.movies.android.data.responses.ResMovie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface
ApiMovie {

    @GET("movie/now_playing")
    fun reqCurrentMovies(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Observable<Res<List<ResMovie>>>

    @GET("movie/popular")
    fun reqPopularMovies(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): Observable<Res<List<ResMovie>>>

    @GET("movie/{id}")
    fun reqMovie(@Path("id") id: String?): Observable<ResMovie>
}
