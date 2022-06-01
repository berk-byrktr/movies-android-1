package com.movies.android.common.ui.extension

infix fun <T> Boolean.then(param: T): T? = if (this) param else null