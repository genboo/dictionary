package ru.spcm.apps.dictionary.tools

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Расширения для ImageView
 * Created by evgeniy on 03.09.2018.
 */
val requestOptions = RequestOptions
        .noTransformation()
        .dontTransform()
        .dontAnimate()


/**
 * Загрузка изображений
 */
fun ImageView.loadImage(image: String) {
    GlideApp
            .with(context)
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(requestOptions)
            .into(this)
}