package ru.spcm.apps.dictionary

import android.app.Application
import ru.spcm.apps.dictionary.di.components.AppComponent
import ru.spcm.apps.dictionary.di.components.DaggerAppComponent

/**
 * Основной класс приложения
 */
class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

}