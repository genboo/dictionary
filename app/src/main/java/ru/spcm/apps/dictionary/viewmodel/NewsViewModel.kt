package ru.spcm.apps.dictionary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.model.data.NewsListResponse
import ru.spcm.apps.dictionary.repos.NewsRepo
import ru.spcm.apps.dictionary.tools.AbsentLiveData

import javax.inject.Inject


/**
 * ViewModel для списка новостей
 */

class NewsViewModel @Inject
internal constructor(newsRepo: NewsRepo) : ViewModel() {

    private val switch = MutableLiveData<String>()
    val news: LiveData<Resource<NewsListResponse>>

    init {
        news = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap newsRepo.getNewsList(it)
            }
            return@switchMap AbsentLiveData.create<Resource<NewsListResponse>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadNews(dateEnd: String) {
        switch.postValue(dateEnd)
    }

}
