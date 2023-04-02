package com.mq.xn.paging3

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper
import com.mx.tool.ktx.toJson

@OptIn(ExperimentalPagingApi::class)
class PagingRemoteMediator : RemoteMediator<Int, CacheEntry>() {
    private var count = 0
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CacheEntry>): MediatorResult {
        Log.d("TAG", "-------->loadType=$loadType")
        val index = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                count++
            }
        }
        val result = Repository.realGetData(index, 20)
        DatabaseHelper.instance.getAppDatabase().withTransaction {
            if (loadType == LoadType.REFRESH) {
                DatabaseHelper.instance.cacheDao().clearAll()
            }
            DatabaseHelper.instance.cacheDao().insert(result)
        }

        return MediatorResult.Success(result.isEmpty())
    }
}