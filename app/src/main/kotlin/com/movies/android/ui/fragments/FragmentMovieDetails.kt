package com.movies.android.ui.fragments

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.movies.android.R
import com.movies.android.common.ui.extension.observeNonNull
import com.movies.android.data.requests.Req
import com.movies.android.databinding.FragmentMovieDetailsBinding
import com.movies.android.ui.adapters.AdapterGenre
import com.movies.android.ui.bases.BaseBindingFragment
import com.movies.android.ui.models.Genre
import com.movies.android.ui.models.Movie
import com.movies.android.ui.viewmodels.ViewModelMovieDetails
import com.movies.android.ui.viewstates.ViewStatePage

class FragmentMovieDetails :
    BaseBindingFragment<ViewModelMovieDetails, FragmentMovieDetailsBinding>() {

    private lateinit var adapter: AdapterGenre
    private var data: MutableList<Genre> = mutableListOf()

    private var id = ""

    companion object {
        @JvmStatic
        fun newInstance(id: String) = FragmentMovieDetails().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    override fun onLayout() = R.layout.fragment_movie_details

    override fun getData() {
        arguments?.getString("id")?.let { id = it }
    }

    override fun onCreateView() {
        init()
        setViews()
        onReqMovie()
    }

    private fun init() {
        vm.dataMovie.observeNonNull(this) { onMovie(it) }
        adapter = AdapterGenre(data)
        binding.rv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rv.adapter = adapter
    }

    private fun setViews() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun onReqMovie() {
        lastRequest = this::onReqMovie
        vm.reqMovie(Req(id = id))
    }

    private fun onMovie(viewState: ViewStatePage<Movie>) {
        with(binding) { viewStatePage = viewState }
        viewState.data?.let {
            binding.movie = it
            data.addAll(it.genres)
            adapter.notifyDataSetChanged()
        }
    }
}