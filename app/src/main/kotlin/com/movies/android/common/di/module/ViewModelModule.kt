package  com.movies.android.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movies.android.common.di.ViewModelFactory
import com.movies.android.common.di.key.ViewModelKey
import com.movies.android.ui.viewmodels.ViewModelMovieDetails
import com.movies.android.ui.viewmodels.ViewModelMovies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(ViewModelMovies::class)
    abstract fun provideMoviesViewModel(vmMovies: ViewModelMovies): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ViewModelMovieDetails::class)
    abstract fun provideMovieDetailsViewModel(vmMovieDetails: ViewModelMovieDetails): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}