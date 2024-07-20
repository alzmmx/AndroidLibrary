package com.mx.tool.ktx

/**
 *
 * @author Mx
 * @date 2023/06/12
 */

fun String?.safeToFloat(def: Float = 0f, parse: ((String) -> Boolean)? = null): Float {
    return safeToFloatOrNull(def, parse)!!
}

fun String?.safeToFloatOrNull(def: Float? = null, parse: ((String) -> Boolean)? = null): Float? {
    if (this.isNullOrBlank() || (parse != null && !parse.invoke(this))) return def
    return try {
        this.toFloat()
    } catch (e: NumberFormatException) {
        e.printLog()
        def
    }
}

fun String?.safeToInt(def: Int = 0, parse: ((String) -> Boolean)? = null): Int {
    return safeToIntOrNull(def, parse)!!
}

fun String?.safeToIntOrNull(def: Int? = null, parse: ((String) -> Boolean)? = null): Int? {
    if (this.isNullOrBlank() || (parse != null && !parse.invoke(this))) return def
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        e.printLog()
        def
    }
}

fun String?.safeToLong(def: Long = 0, parse: ((String) -> Boolean)? = null): Long {
    return safeToLongOrNull(def, parse)!!
}

fun String?.safeToLongOrNull(def: Long? = null, parse: ((String) -> Boolean)? = null): Long? {
    if (this.isNullOrBlank() || (parse != null && !parse.invoke(this))) return def
    return try {
        this.toLong()
    } catch (e: NumberFormatException) {
        e.printLog()
        def
    }
}

fun String?.safeToDouble(def: Double = 0.0, parse: ((String) -> Boolean)? = null): Double {
    return safeToDoubleOrNull(def, parse)!!
}


fun String?.safeToDoubleOrNull(def: Double? = null, parse: ((String) -> Boolean)? = null): Double? {
    if (this.isNullOrBlank() || (parse != null && !parse.invoke(this))) return def
    return toDoubleOrNull() ?: return def
}


fun String?.contact(vararg strings: String?): String {
    val stringBuilder = StringBuilder(this ?: "")
    strings.forEach { stringBuilder.append(it) }
    return stringBuilder.toString()
}

fun String?.contact(vararg any: Any?): String {
    val stringBuilder = StringBuilder(this ?: "")
    any.forEach { stringBuilder.append(it) }
    return stringBuilder.toString()
}