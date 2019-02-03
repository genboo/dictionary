package ru.spcm.apps.dictionary.di.modules

import com.google.gson.GsonBuilder
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.spcm.apps.dictionary.model.api.NewsApi

/**
 * Инициализация Retrofit
 */
@Suppress("unused")
@Module
class NewsRetrofitModule : RetrofitBase() {

    /**
     * Настройка api новостей
     */
    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val builder = GsonBuilder()
        return getRetrofit(API_URL, GsonConverterFactory.create(builder.create()))
    }

    /**
     * Новости
     */
    @Singleton
    @Provides
    internal fun provideNews(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    companion object {
        const val API_URL = "http://news.mosobr.tv"
    }

}
