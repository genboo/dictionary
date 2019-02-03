package ru.spcm.apps.dictionary.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.spcm.apps.dictionary.di.ViewModelFactory
import ru.spcm.apps.dictionary.di.ViewModelKey
import ru.spcm.apps.dictionary.viewmodel.CategoriesViewModel
import ru.spcm.apps.dictionary.viewmodel.NewsByCategoryViewModel
import ru.spcm.apps.dictionary.viewmodel.NewsItemViewModel
import ru.spcm.apps.dictionary.viewmodel.NewsViewModel

/**
 * Инициализация различных ViewModel
 */
@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    internal abstract fun bindCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsItemViewModel::class)
    internal abstract fun bindNewsItemViewModel(viewModel: NewsItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsByCategoryViewModel::class)
    internal abstract fun bindNewsByCategoryViewModel(viewModel: NewsByCategoryViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}