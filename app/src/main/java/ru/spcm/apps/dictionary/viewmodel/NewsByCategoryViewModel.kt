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
 * ViewModel для списка новостей в категории
 */

class NewsByCategoryViewModel @Inject
internal constructor(newsRepo: NewsRepo) : ViewModel() {

    private val switch = MutableLiveData<Params>()
    val news: LiveData<Resource<NewsListResponse>>

    init {
        news = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap newsRepo.getNewsByCategoryList(it.category, it.dateEnd)
            }
            return@switchMap AbsentLiveData.create<Resource<NewsListResponse>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadNews(category: String, dateEnd: String) {
        switch.postValue(Params(category, dateEnd))
    }

    class Params(val category: String, val dateEnd: String)

}
