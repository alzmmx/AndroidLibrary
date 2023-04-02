package com.mq.xn.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper

class RepoPagingSource : PagingSource<Int, CacheEntry>() {
    override fun getRefreshKey(state: PagingState<Int, CacheEntry>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CacheEntry> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        Log.d("TAG", "load--------->page=$page pageSize=$pageSize")
        val data = mutableListOf<CacheEntry>()//Repository.realGetData(page, pageSize)
        val load = DatabaseHelper.instance.cacheDao().queryWithPagingSource().load(params)
        if (load is LoadResult.Page) {
            load.data.mapTo(data) { it.apply { it.data = it.data + "_T" } }
        }
        return LoadResult.Page(data, if (page > 1) page - 1 else null, if (data.isNotEmpty()) page + 1 else null)

    }
}