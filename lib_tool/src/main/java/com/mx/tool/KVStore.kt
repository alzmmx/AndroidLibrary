package com.mx.tool

import android.content.Context
import android.os.Parcelable
import androidx.annotation.MainThread
import com.tencent.mmkv.MMKV

object KVStore {
    private val store by lazy(LazyThreadSafetyMode.NONE) { MMKV.defaultMMKV() }

    @MainThread
    fun init(context: Context) {
        MMKV.initialize(context)
    }

    @JvmStatic
    fun put(key: String, value: Any): Boolean {

        return when (value) {
            is Int -> store.encode(key, value)
            is Float -> store.encode(key, value)
            is Double -> store.encode(key, value)
            is Long -> store.encode(key, value)
            is String -> store.encode(key, value)
            is Parcelable -> store.encode(key, value)
            else -> false
        }
    }

    @JvmStatic
    fun putInt(key: String, value: Int): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun putFloat(key: String, value: Float): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun putDouble(key: String, value: Double): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun putLong(key: String, value: Long): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun putString(key: String, value: String): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun putParcelable(key: String, value: Parcelable): Boolean {
        return store.encode(key, value)
    }

    @JvmStatic
    fun getInt(key: String, default: Int = 0): Int {
        return store.decodeInt(key, default)
    }

    @JvmStatic
    fun getFloat(key: String, default: Float = 0f): Float {
        return store.decodeFloat(key, default)
    }

    @JvmStatic
    fun getDouble(key: String, default: Double = 0.0): Double {
        return store.decodeDouble(key, default)
    }

    @JvmStatic
    fun getString(key: String, default: String = ""): String {
        return store.decodeString(key, default) ?: default
    }

    @JvmStatic
    fun <T : Parcelable> getParcelable(key: String, tClass: Class<T>, default: T? = null): T? {
        return store.decodeParcelable(key, tClass, default)
    }

}