package ru.spcm.apps.dictionary.model.data

import com.google.gson.annotations.SerializedName
import ru.spcm.apps.dictionary.tools.parse

/**
 * Новость
 */
class Post(var id: Int) {

    var title = ""

    var content = ""

    var anounce = ""

    @SerializedName(value = "published_at")
    var published = ""

    val category: Category? = null

    val publishedDate
        get() = published.parse(DATE_PATTERN)

    val imageUrl
        get() = IMAGE_PATH.format(id)

    companion object {
        private const val IMAGE_PATH = "http://news.mosobr.tv/api/v0/posts/%d/image?width=500&height=100&position=center"
        private const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val FORMATTED_PATTERN = "dd/MM/yyyy"
    }
}