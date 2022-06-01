package com.movies.android.common.ui.extension

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(@ColorRes colorInt: Int): Int = ContextCompat.getColor(this, colorInt)