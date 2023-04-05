package com.mq.xn.paging3

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper
import com.mx.tool.util.GsonUtil

class RepoPagingSource : PagingSource<Int, UserData>() {
    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        Log.d("TAG", "load--------->page=$page pageSize=$pageSize")
        val data = mutableListOf<UserData>()//Repository.realGetData(page, pageSize)
        val load = DatabaseHelper.instance.cacheDao().queryWithPagingSource().load(params)
        if (load is LoadResult.Page) {
            load.data.mapTo(data) { GsonUtil.gson.fromJson(it.data, UserData::class.java) }
        }
        return LoadResult.Page(data, if (page > 1) page - 1 else null, if (data.isNotEmpty()) page + 1 else null)

    }
}