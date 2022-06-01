package com.movies.android.common

interface Mapper<R, D> {

    fun mapFrom(type: R): D

}