package com.movies.android.ui.customs

import android.view.View
import android.view.ViewGroup
import com.movies.android.R
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.util.getItemView
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class CustomLoadMore : BaseLoadMoreView() {

    override fun getRootView(parent: ViewGroup): View = parent.getItemView(R.layout.cv_loadmore)

    override fun getLoadingView(holder: BaseViewHolder): View = holder.getView(R.id.flLoading)

    override fun getLoadComplete(holder: BaseViewHolder): View = holder.getView(R.id.flEnd)

    override fun getLoadEndView(holder: BaseViewHolder): View = holder.getView(R.id.flEnd)

    override fun getLoadFailView(holder: BaseViewHolder): View = holder.getView(R.id.flError)
}
