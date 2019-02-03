package ru.spcm.apps.dictionary.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton
/**
 * Инициализация навигатора
 */
@Module
class NavigationModule(private var cicerone: Cicerone<Router> = Cicerone.create()) {

    /**
     * Базовый роутер
     */
    @Provides
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

    /**
     * Базовый холдер для навигации
     */
    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

}