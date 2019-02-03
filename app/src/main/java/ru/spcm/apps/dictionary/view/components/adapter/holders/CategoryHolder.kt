package ru.spcm.apps.dictionary.view.components.adapter.holders

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item_category.view.*
import ru.spcm.apps.dictionary.model.data.Category

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Установка данных
     */
    fun bind(item: Category) = with(itemView) {
        title.text = item.title
        if (!item.isValidColor()) {
            icon.visibility = View.GONE
        } else {
            icon.setColorFilter(Color.parseColor(item.color), PorterDuff.Mode.MULTIPLY)
            icon.visibility = View.VISIBLE
        }
    }


    /**
     * Установка слушателя на нажатие
     */
    fun setListener(listener: View.OnClickListener) {
        itemView.itemBlock?.setOnClickListener(listener)
    }

}