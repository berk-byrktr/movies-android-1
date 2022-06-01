package com.movies.android.ui.adapters

import com.movies.android.R
import com.movies.android.databinding.ItemMoviePopularBinding
import com.movies.android.ui.models.Movie
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

class AdapterMoviesPopular(data: MutableList<Movie>) :
    BaseQuickAdapter<Movie, BaseDataBindingHolder<ItemMoviePopularBinding>>(
        R.layout.item_movie_popular,
        data
    ), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemMoviePopularBinding>, item: Movie) {
        val binding = holder.dataBinding
        binding?.movie = item
        binding?.executePendingBindings()
    }
}