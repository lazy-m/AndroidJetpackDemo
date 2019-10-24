package com.lazy.androidjetpackdemo.Paging

import androidx.paging.ItemKeyedDataSource

class ConcertTimeDataSource():ItemKeyedDataSource<Int,Cheese>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Cheese>
    ) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Cheese>) {

    }

    override fun getKey(item: Cheese): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}