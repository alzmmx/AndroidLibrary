package com.mq.xn.paging3

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object Repository {
    private var pageSize = 20

    private val dataList = mutableListOf<UserData>()

    suspend fun realGetData(page: Int, pageSize: Int): MutableList<UserData> {

        return withContext(Dispatchers.IO) {
            delay(500)
            Log.d("TAG", "--------->page=$page pageSize=$pageSize")
            if (page in 0..10) {
                dataList.subList(page * pageSize, (page + 1) * pageSize)
            } else {
                mutableListOf()
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getData(): Flow<PagingData<UserData>> {
        return Pager(
            config = PagingConfig(pageSize, initialLoadSize = pageSize),
            remoteMediator = PagingRemoteMediator(),
            pagingSourceFactory = { XLimitOffsetPagingSource() }
        ).flow
    }

    fun init() {
        for (i in 100..4000) {
            dataList.add(UserData("$i", "${i / pageSize}-$i"))
        }
    }
}