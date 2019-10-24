package com.lazy.androidjetpackdemo.Paging

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Database Access Object for the Cheese database.
 */
@Dao
interface CheeseDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     * Room知道如何返回LivePagedListProvider，我们可以从中获取LiveData并进行投放
     * 通过ViewModel返回UI。
     */
    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun allCheesesByName(): DataSource.Factory<Int, Cheese>

    /**
     * 增
     */
    @Insert
    fun insert(cheeses: List<Cheese>)
    /*
       增
     */
    @Insert
    fun insert(cheese: Cheese)

    /**
     *删
     */
    @Delete
    fun delete(cheese: Cheese)
}