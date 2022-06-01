package  com.movies.android.common.di.module

import com.movies.android.common.di.scope.FragmentScope
import com.movies.android.ui.fragments.FragmentMovieDetails
import com.movies.android.ui.fragments.FragmentMovies
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragmentMovies(): FragmentMovies

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun fragmentMovieDetail(): FragmentMovieDetails
}