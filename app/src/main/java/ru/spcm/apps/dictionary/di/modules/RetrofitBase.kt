package ru.spcm.apps.dictionary.di.modules

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.spcm.apps.dictionary.BuildConfig
import ru.spcm.apps.dictionary.di.LiveDataCallAdapterFactory
import ru.spcm.apps.dictionary.tools.Logger
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * Базовый класс для доступа к сети
 */
open class RetrofitBase {

    /**
     * Создание и настройка объекта Retrofit
     */
    fun getRetrofit(api: String, factory: Converter.Factory): Retrofit {
        val httpClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)

        setRetryOptions(httpClient)
        setDebugOptions(httpClient)

        return Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(factory)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(httpClient.build())
                .build()
    }

    /**
     * Установка опции повторных запросов в случае ошибки
     */
    private fun setRetryOptions(httpClient: OkHttpClient.Builder) {
        httpClient.addInterceptor { chain ->

            val request = chain.request()
            var response: Response? = null
            var tryCount = 0
            var errorMessage = ""
            while (response == null && tryCount < TRY_LIMIT) {
                try {
                    response = chain.proceed(request)
                    if (response.isSuccessful) {
                        break
                    }
                } catch (ex: Exception) {
                    Logger.e(ex)
                    errorMessage = ex.message ?: "Request error"
                }
                Logger.d("Retry request")
                tryCount++
            }
            return@addInterceptor response ?: Response
                    .Builder()
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .message(errorMessage)
                    .code(500)
                    .body(ResponseBody.create(MediaType.parse("text/html"), "{\"code\":200, \"error\":\"$errorMessage\"}"))
                    .build()
        }
    }

    /**
     * Устанавка опции для отладки
     */
    private fun setDebugOptions(httpClient: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG_MODE) {
            //Логгер в режиме отладки для всех запросов
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
    }

    companion object {
        const val READ_TIMEOUT: Long = 15
        const val CONNECT_TIMEOUT: Long = 15
        const val TRY_LIMIT: Int = 3
    }
}