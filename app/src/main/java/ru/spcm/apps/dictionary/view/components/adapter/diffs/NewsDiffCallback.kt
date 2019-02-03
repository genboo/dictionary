package ru.spcm.apps.dictionary.view.components.adapter.diffs

import android.support.v7.util.DiffUtil
import ru.spcm.apps.dictionary.model.data.Post

class NewsDiffCallback(private val oldList: List<Post>,
                       private val newList: List<Post>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}