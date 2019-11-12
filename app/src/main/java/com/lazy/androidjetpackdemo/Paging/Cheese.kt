package com.lazy.androidjetpackdemo.Paging

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents our items.
 */
@Entity
data class Cheese(@PrimaryKey(autoGenerate = true) val id: Int,
                  val  img :String ="https://www.baidu.com/img/bd_logo1.png",
                  val name: String)