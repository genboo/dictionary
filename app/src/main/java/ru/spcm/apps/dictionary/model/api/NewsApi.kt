package ru.spcm.apps.dictionary.model.api

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spcm.apps.dictionary.di.ApiResponse
import ru.spcm.apps.dictionary.model.data.CategoriesResponse
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.model.data.NewsListResponse

/**
 * API для получения новостей
 */
interface NewsApi {

    @GET("/api/v0/categories/")
    fun getCategories(): LiveData<ApiResponse<CategoriesResponse>>

    @GET("/api/v0/posts?order_by[published_at]=desc")
    fun getNewsList(@Query("page_limit") limit: Int, @Query("date_end") dateEnd: String): LiveData<ApiResponse<NewsListResponse>>

    @GET("/api/v0/posts?order_by[published_at]=desc")
    fun getNewsListByCategory(@Query("category") category: String, @Query("page_limit") limit: Int, @Query("date_end") dateEnd: String): LiveData<ApiResponse<NewsListResponse>>

    @GET("/api/v0/posts/{id}")
    fun getNews(@Path("id") id: Int): LiveData<ApiResponse<Post>>

}