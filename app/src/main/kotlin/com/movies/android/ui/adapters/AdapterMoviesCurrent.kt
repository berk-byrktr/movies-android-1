package com.movies.android.ui.adapters

import com.movies.android.R
import com.movies.android.databinding.ItemMovieCurrentBinding
import com.movies.android.ui.models.Movie
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

class AdapterMoviesCurrent(data: MutableList<Movie>) :
    BaseQuickAdapter<Movie, BaseDataBindingHolder<ItemMovieCurrentBinding>>(
        R.layout.item_movie_current,
        data
    ) {

    override fun convert(holder: BaseDataBindingHolder<ItemMovieCurrentBinding>, item: Movie) {
        val binding = holder.dataBinding
        binding?.movie = item
        binding?.executePendingBindings()
    }
}