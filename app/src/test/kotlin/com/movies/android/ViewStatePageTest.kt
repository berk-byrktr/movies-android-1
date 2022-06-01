package com.movies.android

import com.movies.android.common.Status
import com.movies.android.ui.models.Movies
import com.movies.android.ui.viewstates.ViewStatePage
import com.google.common.truth.Truth
import org.junit.Test

class ViewStatePageTest {

    @Test
    fun `should return loading true when status is loading`() {

        // Given
        val givenViewState = ViewStatePage<Movies>(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {

        // Given
        val givenViewState = ViewStatePage<Movies>(status = Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {

        // Given
        val givenViewState = ViewStatePage<Movies>(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }
}