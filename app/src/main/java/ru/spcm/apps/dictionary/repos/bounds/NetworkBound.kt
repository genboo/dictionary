package ru.spcm.apps.dictionary.repos.bounds

import android.support.annotation.MainThread
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.tools.AppExecutors

/**
 * Базовый класс для сетевых запросов без использования кэша
 */
abstract class NetworkBound<R>
@MainThread
internal constructor(appExecutors: AppExecutors) : NetworkBoundBase<R>(appExecutors) {

    override fun create(): NetworkBound<R> {
        result.value = Resource.loading(null)
        val savedData = loadSaved()
        result.addSource(savedData) { data ->
            if (shouldFetch(data)) {
                result.removeSource(savedData)
                result.value = Resource.loading(data)
                fetchFromNetwork(data)
            } else {
                result.value = Resource.success(data)
            }
        }

        return this
    }

}