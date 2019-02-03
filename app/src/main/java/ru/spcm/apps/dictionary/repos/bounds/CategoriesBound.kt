package ru.spcm.apps.dictionary.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.spcm.apps.dictionary.di.ApiResponse
import ru.spcm.apps.dictionary.model.api.NewsApi
import ru.spcm.apps.dictionary.model.data.CategoriesResponse
import ru.spcm.apps.dictionary.tools.AppExecutors

class CategoriesBound(appExecutors: AppExecutors,
                      private val newsApi: NewsApi) : NetworkBound<CategoriesResponse>(appExecutors) {

    private var response: CategoriesResponse? = null

    override fun shouldFetch(data: CategoriesResponse?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<CategoriesResponse> {
        val result = MutableLiveData<CategoriesResponse>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: CategoriesResponse?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<CategoriesResponse>> {
        return newsApi.getCategories()
    }

}