package ru.spcm.apps.dictionary.model.data

import com.google.gson.annotations.SerializedName

class Category {

    var id = 0

    var title: String = ""

    var description: String? = null

    @SerializedName(value = "created_at")
    var created: String = ""

    @SerializedName(value = "updated_at")
    var updated: String? = null

    @SerializedName(value = "deleted_at")
    var deleted: String? = null

    var slug = ""

    var color: String? = null

    var image = ""

    @SerializedName(value = "image_map")
    var imageMap = ""

    @SerializedName(value = "image_map_active")
    var image_map_active = ""

    fun isValidColor(): Boolean {
        return color != null && color?.length == 7
    }
}