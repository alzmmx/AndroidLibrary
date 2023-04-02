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

    private val dataList = mutableListOf<CacheEntry>()

    suspend fun realGetData(page: Int, pageSize: Int): MutableList<CacheEntry> {

        return withContext(Dispatchers.IO) {
            delay(3000)
            Log.d("TAG", "--------->page=$page pageSize=$pageSize")
            dataList.subList(page * pageSize, (page + 1) * pageSize)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getData(): Flow<PagingData<CacheEntry>> {
        return Pager(
            config = PagingConfig(pageSize, initialLoadSize = 20),
            remoteMediator = PagingRemoteMediator(),
            pagingSourceFactory = { RepoPagingSource() }
        ).flow
    }

    fun init() {
        val id = SimpleDateFormat("yyyy_MM_dd_HH_mm", Locale.getDefault()).format(Date())
        for (i in 0..1000) {
            dataList.add(CacheEntry("$id-$i", "$${i / 20}-$i"))
        }
    }
}