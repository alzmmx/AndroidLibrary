package com.mq.xn.paging3

import com.mx.tool.database.CacheEntry

sealed class VideoUIState {

    object Idle : VideoUIState()

    data class Loading(val show: Boolean) : VideoUIState()

    data class Toast(val msg: String) : VideoUIState()

    data class LoadError(val error: Exception) : VideoUIState()

    data class LoadSuccess(val reqData: MutableList<CacheEntry>) : VideoUIState()

    data class Success(val msg: String? = "") : VideoUIState()


}
