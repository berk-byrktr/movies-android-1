package com.movies.android

import android.content.Context
import android.content.Intent
import com.movies.android.ui.activities.ActivityMain

class Navigator {

    companion object {

        fun openMain(context: Context?) = Intent(context, ActivityMain::class.java)

    }
}