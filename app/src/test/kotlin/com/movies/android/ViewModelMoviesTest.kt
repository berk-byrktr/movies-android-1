package com.movies.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movies.RxImmediateSchedulerRule
import com.movies.android.common.Resource
import com.movies.android.data.requests.Req
import com.movies.android.domain.interactors.UseCaseMovies
import com.movies.android.domain.interactors.UseCasePopularMovies
import com.movies.android.ui.models.Movies
import com.movies.android.ui.viewmodels.ViewModelMovies
import com.movies.android.ui.viewstates.ViewStatePage
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelMoviesTest {

    @MockK
    lateinit var ucMovies: UseCaseMovies

    @MockK
    lateinit var ucPopularMovies: UseCasePopularMovies

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var viewModelMovies: ViewModelMovies

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelMovies = ViewModelMovies(ucMovies, ucPopularMovies)
    }

    @Test
    fun `given loading state, when reqMovies called, then isLoading return true`() {

        // Given
        val mockedObserver = createMoviesObserver()
        viewModelMovies.dataMovies.observeForever(mockedObserver)

        every {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        } returns Observable.just(Resource.loading())

        // When
        viewModelMovies.reqMovies(
            req1 = Req(language = "en-US", page = 1),
            req2 = Req(language = "en-US", page = 1)
        )

        // Then
        val slot = slot<ViewStatePage<Movies>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isTrue()

        verify {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        }
    }

    @Test
    fun `given error state, when reqMovies called, then update live data for error status`() {
        // Given
        val mockedObserver = createMoviesObserver()
        viewModelMovies.dataMovies.observeForever(mockedObserver)
        val resource = Resource.error<Movies>(Throwable())

        every {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        } returns Observable.just(resource)

        // When
        viewModelMovies.reqMovies(
            req1 = Req(language = "en-US", page = 1),
            req2 = Req(language = "en-US", page = 1)
        )

        // Then
        val slot = slot<ViewStatePage<Movies>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isFalse()

        verify {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        }
    }

    @Test
    fun `given succcess state, when reqMovies called, then isLoading return false`() {
        // Given
        val mockedObserver = createMoviesObserver()
        viewModelMovies.dataMovies.observeForever(mockedObserver)
        val resource = Resource.success(Movies(mutableListOf(), mutableListOf()))

        every {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        } returns Observable.just(resource)


        // When
        viewModelMovies.reqMovies(
            req1 = Req(language = "en-US", page = 1),
            req2 = Req(language = "en-US", page = 1)
        )

        // Then
        val slot = slot<ViewStatePage<Movies>>()
        verify { mockedObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.isLoading()).isFalse()

        verify {
            ucMovies.reqMovies(
                req1 = Req(language = "en-US", page = 1),
                req2 = Req(language = "en-US", page = 1)
            )
        }
    }

    private fun createMoviesObserver(): Observer<ViewStatePage<Movies>> =
        spyk(Observer { })
}