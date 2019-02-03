package ru.spcm.apps.dictionary.model.data

import com.google.gson.annotations.SerializedName

class NewsListResponse {

    var currentPage = 0

    var perPage = 0

    var lastPage = 0

    var pages = 0

    var count = 0

    @SerializedName("search_by")
    var searchBy: SearchBy? = null

    val posts: List<Post>? = null
}