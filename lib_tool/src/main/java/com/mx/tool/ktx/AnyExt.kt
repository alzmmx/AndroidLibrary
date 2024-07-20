package com.mx.tool.ktx

import com.mx.tool.util.GsonUtil

fun Any.toJson(): String {
    return GsonUtil.gson.toJson(this)
}
inline fun <reified T> Boolean.ternary(ifTrue: T, ifFalse: () -> T): T {
    return if (this) ifTrue else ifFalse()
}

inline fun <reified T> Boolean.ternary(ifTrue: () -> T, ifFalse: () -> T): T {
    return if (this) ifTrue() else ifFalse()
}

inline fun <reified T> Boolean.ternary(ifTrue: T, ifFalse: T): T {
    return if (this) ifTrue else ifFalse
}