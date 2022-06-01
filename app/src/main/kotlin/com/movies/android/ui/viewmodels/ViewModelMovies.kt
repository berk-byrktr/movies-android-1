package com.movies.android.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.movies.android.common.Resource
import com.movies.android.common.ui.extension.plusAssign
import com.movies.android.data.requests.Req
import com.movies.android.domain.interactors.UseCaseMovies
import com.movies.android.domain.interactors.UseCasePopularMovies
import com.movies.android.ui.bases.BaseViewModel
import com.movies.android.ui.models.Movie
import com.movies.android.ui.models.Movies
import com.movies.android.ui.viewstates.ViewStatePage
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ViewModelMovies @Inject constructor(
    private val ucMovies: UseCaseMovies,
    private val ucPopularMovies: UseCasePopularMovies
) : BaseViewModel() {

    val dataMovies = MutableLiveData<ViewStatePage<Movies>>()
    val dataPopularMovies = MutableLiveData<ViewStatePage<List<Movie>>>()

    fun reqMovies(req1: Req, req2: Req) {
        ucMovies
            .reqMovies(req1, req2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovies)
            .also { disposable += it }
    }

    fun reqPopularMovies(req: Req) {
        ucPopularMovies
            .reqPopularMovies(req)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onPopularMovies)
            .also { disposable += it }
    }

    private fun onMovies(res: Resource<Movies>) {
        dataMovies.value = ViewStatePage(res.status, res.error, res.data)
    }

    private fun onPopularMovies(res: Resource<List<Movie>>) {
        dataPopularMovies.value = ViewStatePage(res.status, res.error, res.data)
    }
}