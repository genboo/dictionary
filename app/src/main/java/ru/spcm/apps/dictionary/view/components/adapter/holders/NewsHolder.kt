package ru.spcm.apps.dictionary.view.components.adapter.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item_news.view.*
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.tools.format

class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Установка данных
     */
    fun bind(item: Post) = with(itemView) {
        title.text = item.title
        date.text = item.publishedDate.format(Post.FORMATTED_PATTERN)
        anounce.text = item.anounce
    }


    /**
     * Установка слушателя на нажатие
     */
    fun setListener(listener: View.OnClickListener) {
        itemView.itemBlock?.setOnClickListener(listener)
    }

}