package ru.spcm.apps.dictionary.di.components

import android.content.Context

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import ru.spcm.apps.dictionary.di.modules.NavigationModule
import ru.spcm.apps.dictionary.di.modules.NewsRetrofitModule
import ru.spcm.apps.dictionary.di.modules.ViewModelModule
import ru.spcm.apps.dictionary.view.activities.MainActivity
import ru.spcm.apps.dictionary.view.fragments.BaseFragment

/**
 * Основной компонент dagger 2 для инъекции зависимостей
 */
@Component(modules = [
    ViewModelModule::class,
    NewsRetrofitModule::class,
    NavigationModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }


    fun inject(fragment: BaseFragment)

    fun inject(activity: MainActivity)

}