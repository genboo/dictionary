package ru.spcm.apps.dictionary.repos

import android.arch.lifecycle.LiveData
import ru.spcm.apps.dictionary.model.api.NewsApi
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.model.data.NewsListResponse
import ru.spcm.apps.dictionary.repos.bounds.NewsBound
import ru.spcm.apps.dictionary.repos.bounds.NewsByCategoryListBound
import ru.spcm.apps.dictionary.repos.bounds.NewsListBound
import ru.spcm.apps.dictionary.tools.AppExecutors
import javax.inject.Inject

class NewsRepo @Inject
constructor(private val appExecutors: AppExecutors,
            private val newsApi: NewsApi) {


    fun getNewsList(dateEnd: String): LiveData<Resource<NewsListResponse>> {
        return NewsListBound(appExecutors, newsApi)
                .setParams(dateEnd)
                .create()
                .asLiveData()
    }

    fun getNewsByCategoryList(category: String, dateEnd: String): LiveData<Resource<NewsListResponse>> {
        return NewsByCategoryListBound(appExecutors, newsApi)
                .setParams(category, dateEnd)
                .create()
                .asLiveData()
    }

    fun getNews(id: Int): LiveData<Resource<Post>> {
        return NewsBound(appExecutors, newsApi)
                .setParams(id)
                .create()
                .asLiveData()
    }

}