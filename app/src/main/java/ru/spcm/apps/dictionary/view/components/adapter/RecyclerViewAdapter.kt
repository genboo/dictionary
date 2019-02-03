package ru.spcm.apps.dictionary.view.components.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.ArrayList

/**
 * Базовый класс адаптеров для списков
 */
abstract class RecyclerViewAdapter<T, H : RecyclerView.ViewHolder>
internal constructor() : RecyclerView.Adapter<H>() {

    private var items: List<T> = ArrayList()

    private var listener: (Int, T, View?) -> Unit = { _, _, _ -> }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Реальное кол-во элементов
     */
    fun getSize(): Int {
        return items.size
    }

    /**
     * Установка элементов списка
     */
    open fun setItems(items: List<T>) {
        this.items = items
    }

    /**
     * Получение элемента списка
     */
    fun getItem(position: Int): T {
        return items[position]
    }

    /**
     * Получение всех элементов
     */
    fun getItems(): List<T> {
        return items
    }

    /**
     * Установка слушателя на нажатие на элементе списка
     */
    fun setOnItemClickListener(event: (Int, T, View?) -> Unit) {
        listener = event
    }

    /**
     * Событие нажатия на элементе списка
     */
    internal fun onItemClick(view: View, position: Int, imageView: View? = null) {
        if (position != RecyclerView.NO_POSITION && position < items.size) {
            view.postDelayed({ listener(position, items[position], imageView) },
                    DEFAULT_CLICK_DELAY
            )
        }
    }

    companion object {
        const val DEFAULT_CLICK_DELAY: Long = 100
    }

}