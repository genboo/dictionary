package ru.spcm.apps.dictionary.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import ru.spcm.apps.dictionary.di.ApiResponse
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.tools.AppExecutors
import ru.spcm.apps.dictionary.tools.Logger


/**
 * Базовый класс для сетевых запросов
 * Created by evgeniy on 10.09.2018.
 */
abstract class NetworkBoundBase<R>
@MainThread
internal constructor(private val appExecutors: AppExecutors) {
    val result = MediatorLiveData<Resource<R>>()

    fun fetchFromNetwork(data: R?) {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            if (response?.body != null) {
                appExecutors.networkIO().execute {
                    saveResult(processResponse(response))
                    appExecutors.mainThread().execute {
                        val saved = loadSaved()
                        result.addSource(saved) { newData ->
                            result.setValue(Resource.success(newData))
                        }
                    }
                }
            } else {
                onFetchFailed(response?.errorMessage)
                result.setValue(Resource.error(response?.errorMessage
                        ?: "Ошибка получения данных", data))
            }
        }
    }

    abstract fun create(): NetworkBoundBase<R>

    /**
     * Результат работы в виде LiveData
     */
    fun asLiveData(): LiveData<Resource<R>> {
        return result
    }

    /**
     * Обработка тела запроса
     */
    @WorkerThread
    private fun processResponse(response: ApiResponse<R>?): R? {
        return response?.body
    }

    /**
     * Логирование ошибки запроса
     */
    private fun onFetchFailed(message: String?) {
        Logger.e(message)
    }

    /**
     * Нужно ли загружать данные
     */
    @MainThread
    protected abstract fun shouldFetch(data: R?): Boolean

    /**
     * Загрузка ранее сохраненных данных
     */
    @MainThread
    protected abstract fun loadSaved(): LiveData<R>

    /**
     * Сохранение данных
     */
    @WorkerThread
    protected abstract fun saveResult(data: R?)

    /**
     * Запрос к серверу
     */
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<R>>
}