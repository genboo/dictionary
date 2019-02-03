package ru.spcm.apps.dictionary.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.spcm.apps.dictionary.di.ApiResponse
import ru.spcm.apps.dictionary.model.api.NewsApi
import ru.spcm.apps.dictionary.model.data.NewsListResponse
import ru.spcm.apps.dictionary.tools.AppExecutors

class NewsListBound(appExecutors: AppExecutors,
                    private val newsApi: NewsApi) : NetworkBound<NewsListResponse>(appExecutors) {

    private var response: NewsListResponse? = null

    private var dateEnd = ""

    fun setParams(dateEnd: String): NewsListBound {
        this.dateEnd = dateEnd
        return this
    }

    override fun shouldFetch(data: NewsListResponse?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<NewsListResponse> {
        val result = MutableLiveData<NewsListResponse>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: NewsListResponse?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<NewsListResponse>> {
        return newsApi.getNewsList(LIMIT, dateEnd)
    }

    companion object {
        private const val LIMIT = 10
    }
}