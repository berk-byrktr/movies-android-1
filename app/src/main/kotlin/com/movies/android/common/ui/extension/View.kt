package com.movies.android.common.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

internal fun <T : View> View?.findView(@IdRes id: Int, block: T.() -> Unit) {
    this?.findViewById<T>(id)?.let { block.invoke(it) }
}

fun View.inflateLayout(@LayoutRes layoutId: Int): View? {
    return LayoutInflater.from(context).inflate(layoutId, null)
}

internal fun View?.visible() = visible { }

internal fun View?.visible(block: (View) -> Unit) {
    this?.apply {
        visibility = View.VISIBLE
        block.invoke(this)
    }
}

internal fun View?.gone() = gone { }

internal fun View?.gone(block: (View) -> Unit) {
    this?.apply {
        visibility = View.GONE
        block.invoke(this)
    }
}

internal fun View?.startViewAnimation(@IdRes id: Int, animation: Animation?) {
    animation?.let {
        findView<View>(id) {
            clearAnimation()
            startAnimation(it)
        }
    }
}

internal fun View?.clearViewAnimation(@IdRes id: Int) {
    findView<View>(id) { clearAnimation() }
}


