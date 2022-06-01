package com.movies.android.ui.activities

import com.movies.android.Navigator
import com.movies.android.common.ui.extension.setImmersive
import com.movies.android.ui.bases.BaseActivity

class ActivitySplash : BaseActivity() {

    override fun onCreate() {
        setViews()
        start()
    }

    private fun setViews() {
        window.setImmersive()
    }

    private fun start() {
        startActivity(Navigator.openMain(this))
        finish()
    }
}