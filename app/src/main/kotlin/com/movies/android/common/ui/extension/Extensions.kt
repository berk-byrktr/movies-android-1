package com.movies.android.common.ui.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.movies.android.common.Resource
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun <T> applyLoading(): ObservableTransformer<Resource<T>, Resource<T>> =
    ObservableTransformer { upstream ->
        Observable.just(Resource.loading<T>()).concatWith(upstream)
    }

fun <T> applyContent(): ObservableTransformer<Resource<T>, Resource<T>> =
    ObservableTransformer { upstream ->
        Observable.just(Resource.content<T>()).concatWith(upstream)
    }

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun Any?.runIfNull(block: () -> Unit) {
    if (this == null) block()
}

fun Any?.runIfNotNull(block: () -> Unit) {
    if (this != null) block()
}