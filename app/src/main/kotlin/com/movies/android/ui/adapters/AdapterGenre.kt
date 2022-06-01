package com.movies.android.ui.adapters

import com.movies.android.R
import com.movies.android.databinding.ItemGenreBinding
import com.movies.android.ui.models.Genre
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

class AdapterGenre(data: MutableList<Genre>) :
    BaseQuickAdapter<Genre, BaseDataBindingHolder<ItemGenreBinding>>(
        R.layout.item_genre,
        data
    ) {

    override fun convert(holder: BaseDataBindingHolder<ItemGenreBinding>, item: Genre) {
        val binding = holder.dataBinding
        binding?.genre = item
        binding?.executePendingBindings()
    }
}