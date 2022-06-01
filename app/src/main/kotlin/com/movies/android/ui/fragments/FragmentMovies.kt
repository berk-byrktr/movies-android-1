package com.movies.android.ui.fragments

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.android.R
import com.movies.android.common.ui.extension.observeNonNull
import com.movies.android.data.requests.Req
import com.movies.android.databinding.FragmentMoviesBinding
import com.movies.android.ui.adapters.AdapterMoviesCurrent
import com.movies.android.ui.adapters.AdapterMoviesPopular
import com.movies.android.ui.bases.BaseActivity
import com.movies.android.ui.bases.BaseBindingFragment
import com.movies.android.ui.customs.CustomLoadMore
import com.movies.android.ui.models.Movie
import com.movies.android.ui.models.Movies
import com.movies.android.ui.viewmodels.ViewModelMovies
import com.movies.android.ui.viewstates.ViewStatePage
import kotlinx.android.synthetic.main.header_fragment_movies.view.*

class FragmentMovies : BaseBindingFragment<ViewModelMovies, FragmentMoviesBinding>() {

    private lateinit var adapterMoviesCurrent: AdapterMoviesCurrent
    private lateinit var adapterMoviesPopular: AdapterMoviesPopular
    private var dataMoviesCurrent: MutableList<Movie> = mutableListOf()
    private var dataMoviesPopular: MutableList<Movie> = mutableListOf()

    private var page = 2

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMovies()

        const val FIRST_PAGE = 1
        const val ENGLISH = "en-US"
    }

    override fun onLayout() = R.layout.fragment_movies

    override fun onCreateView() {
        init()
        setViews()
        onReqMovies()
    }

    private fun init() {
        vm.dataMovies.observeNonNull(this) { onMovies(it) }
        vm.dataPopularMovies.observeNonNull(this) { onPopularMovies(it) }
        adapterMoviesCurrent = AdapterMoviesCurrent(dataMoviesCurrent)
        adapterMoviesPopular = AdapterMoviesPopular(dataMoviesPopular).apply {
            loadMoreModule.loadMoreView = CustomLoadMore()
            loadMoreModule.isAutoLoadMore = true
            loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        }
        binding.rvMoviesPopular.layoutManager = LinearLayoutManager(context)
        binding.rvMoviesPopular.adapter = adapterMoviesPopular
        adapterMoviesPopular.addHeaderView(LayoutInflater.from(context)
            .inflate(R.layout.header_fragment_movies, null, false)
            .apply {
                rvCurrentMovies.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                rvCurrentMovies.adapter = adapterMoviesCurrent
            })
        setCustomPage(binding.cp)
    }

    private fun setViews() {
        adapterMoviesCurrent.setOnItemClickListener { _, _, position ->
            (activity as BaseActivity).addFragment(
                FragmentMovieDetails.newInstance(
                    dataMoviesCurrent[position].id
                )
            )
        }
        adapterMoviesPopular.setOnItemClickListener { _, _, position ->
            (activity as BaseActivity).addFragment(
                FragmentMovieDetails.newInstance(
                    dataMoviesPopular[position].id
                )
            )
        }
        adapterMoviesPopular.loadMoreModule.setOnLoadMoreListener { onReqPopularMovies() }
    }

    private fun onReqMovies() {
        lastRequest = this::onReqMovies
        vm.reqMovies(
            Req(language = ENGLISH, page = FIRST_PAGE),
            Req(language = ENGLISH, page = FIRST_PAGE)
        )
    }

    private fun onMovies(viewState: ViewStatePage<Movies>) {
        with(binding) { viewStatePage = viewState }
        viewState.data?.let {
            dataMoviesCurrent.clear()
            dataMoviesPopular.clear()
            dataMoviesCurrent.addAll(it.currentMovies)
            dataMoviesPopular.addAll(it.currentMovies)
            adapterMoviesCurrent.notifyDataSetChanged()
            adapterMoviesPopular.notifyDataSetChanged()
        }
    }

    private fun onReqPopularMovies() {
        lastRequest = this::onReqPopularMovies
        vm.reqPopularMovies(Req(language = ENGLISH, page = page))
    }

    private fun onPopularMovies(viewState: ViewStatePage<List<Movie>>) {
        with(binding) { viewStatePage = viewState }
        viewState.data?.let {
            if (page == FIRST_PAGE) dataMoviesPopular.clear()
            dataMoviesPopular.addAll(it)
            adapterMoviesPopular.notifyDataSetChanged()
            adapterMoviesPopular.loadMoreModule.isEnableLoadMore = true
            adapterMoviesPopular.loadMoreModule.loadMoreComplete()
            page += 1
            if (it.isEmpty()) adapterMoviesPopular.loadMoreModule.loadMoreEnd(true)
        }
        if (viewState.isLoadingMoreError()) adapterMoviesPopular.loadMoreModule.loadMoreFail()
    }
}