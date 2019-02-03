package ru.spcm.apps.dictionary.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.spcm.apps.dictionary.di.ApiResponse
import ru.spcm.apps.dictionary.model.api.NewsApi
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.tools.AppExecutors

class NewsBound(appExecutors: AppExecutors,
                private val newsApi: NewsApi) : NetworkBound<Post>(appExecutors) {

    private var response: Post? = null

    private var id = 0

    fun setParams(id: Int): NewsBound {
        this.id = id
        return this
    }

    override fun shouldFetch(data: Post?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<Post> {
        val result = MutableLiveData<Post>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: Post?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<Post>> {
        return newsApi.getNews(id)
    }

}