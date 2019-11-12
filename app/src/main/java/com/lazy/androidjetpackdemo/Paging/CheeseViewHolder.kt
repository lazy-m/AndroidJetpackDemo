package com.lazy.androidjetpackdemo.Paging

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.lazy.androidjetpackdemo.R

/**
 * A simple ViewHolder that can bind a Cheese item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 * 一个可以绑定奶酪项目的简单ViewHolder。它也接受空项目，因为数据可能在绑定之前尚未获取。
 */
class CheeseViewHolder(parent :ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_concert, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var cheese : Cheese? = null
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     * 如果尚未分页，则这些项目可能为null。 PagedListAdapter将重新绑定加载Item时的ViewHolder。
     */
    fun bindTo(cheese : Cheese?) {
        this.cheese = cheese
        nameView.text = cheese?.name
    }
}