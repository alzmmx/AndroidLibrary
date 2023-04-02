package com.mq.xn.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mx.tool.database.CacheEntry
import kotlinx.coroutines.flow.Flow

class PagingViewModel : ViewModel() {

    fun getData(): Flow<PagingData<CacheEntry>> {
        return Repository.getData().cachedIn(viewModelScope)
    }
}