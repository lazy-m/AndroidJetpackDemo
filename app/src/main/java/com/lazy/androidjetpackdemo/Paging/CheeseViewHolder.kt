package com.lazy.androidjetpackdemo.Paging

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.lazy.androidjetpackdemo.R

/**
 * A simple ViewHolder that can bind a Cheese item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class CheeseViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_concert, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var cheese : Cheese? = null
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(cheese : Cheese?) {
        this.cheese = cheese
        nameView.text = cheese?.name
    }
}