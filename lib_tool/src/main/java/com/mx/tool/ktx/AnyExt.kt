package com.mx.tool.ktx

import com.mx.tool.util.GsonUtil

fun Any.toJson(): String {
    return GsonUtil.gson.toJson(this)
}