package com.movies.android.ui.activities

import com.movies.android.R
import com.movies.android.ui.bases.BaseActivity
import com.movies.android.ui.fragments.FragmentMovies

class ActivityMain : BaseActivity() {

    override fun onLayout() = R.layout.activity_main

    override fun onCreate() {
        setViews()
    }

    private fun setViews() {
        openFragment(FragmentMovies.newInstance())
    }
}