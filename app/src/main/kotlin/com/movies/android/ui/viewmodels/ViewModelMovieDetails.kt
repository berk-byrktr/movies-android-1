package com.movies.android.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.movies.android.common.Resource
import com.movies.android.common.ui.extension.plusAssign
import com.movies.android.data.requests.Req
import com.movies.android.domain.interactors.UseCaseMovie
import com.movies.android.ui.bases.BaseViewModel
import com.movies.android.ui.models.Movie
import com.movies.android.ui.viewstates.ViewStatePage
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ViewModelMovieDetails @Inject constructor(
    private val ucMovie: UseCaseMovie,
) : BaseViewModel() {

    val dataMovie = MutableLiveData<ViewStatePage<Movie>>()

    fun reqMovie(req: Req) {
        ucMovie
            .reqMovie(req)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMovie)
            .also { disposable += it }
    }

    private fun onMovie(res: Resource<Movie>) {
        dataMovie.value = ViewStatePage(res.status, res.error, res.data)
    }
}