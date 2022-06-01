package com.movies.android

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ViewStatePageTest::class,
    ViewModelMoviesTest::class
)
class MoviesTestSuite