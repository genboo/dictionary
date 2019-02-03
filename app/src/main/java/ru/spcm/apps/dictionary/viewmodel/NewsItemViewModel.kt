package ru.spcm.apps.dictionary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.repos.NewsRepo
import ru.spcm.apps.dictionary.tools.AbsentLiveData

import javax.inject.Inject


/**
 * ViewModel для новости
 */

class NewsItemViewModel @Inject
internal constructor(newsRepo: NewsRepo) : ViewModel() {

    private val switch = MutableLiveData<Int>()
    val post: LiveData<Resource<Post>>

    init {
        post = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap newsRepo.getNews(it)
            }
            return@switchMap AbsentLiveData.create<Resource<Post>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadNews(id: Int) {
        switch.postValue(id)
    }

}
