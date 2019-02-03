package ru.spcm.apps.dictionary.view.components.adapter

import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spcm.apps.dictionary.R
import ru.spcm.apps.dictionary.model.data.Post
import ru.spcm.apps.dictionary.view.components.adapter.diffs.NewsDiffCallback
import ru.spcm.apps.dictionary.view.components.adapter.holders.NewsHolder

class NewsListAdapter  : RecyclerViewAdapter<Post, NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val holder = NewsHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false))
        holder.setListener(View.OnClickListener { v -> onItemClick(v, holder.adapterPosition) })
        return holder
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    override fun setItems(items: List<Post>) {
        val diffs = DiffUtil.calculateDiff(NewsDiffCallback(getItems(), items), !getItems().isEmpty())
        super.setItems(items)
        diffs.dispatchUpdatesTo(this)
    }

}