package ru.spcm.apps.dictionary.repos

import android.arch.lifecycle.LiveData
import ru.spcm.apps.dictionary.model.api.NewsApi
import ru.spcm.apps.dictionary.model.data.CategoriesResponse
import ru.spcm.apps.dictionary.model.data.Resource
import ru.spcm.apps.dictionary.repos.bounds.CategoriesBound
import ru.spcm.apps.dictionary.tools.AppExecutors
import javax.inject.Inject

class CategoriesRepo @Inject
constructor(private val appExecutors: AppExecutors,
            private val newsApi: NewsApi) {


    fun getCategories(): LiveData<Resource<CategoriesResponse>> {
        return CategoriesBound(appExecutors, newsApi)
                .create()
                .asLiveData()
    }

}