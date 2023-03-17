package com.mq.network

import ct4.lib.tool.utils.LogUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 *
 * @author Mx
 * @date 2023/02/23
 */
object RetrofitManager {
    private const val DEFAULT_TIMEOUT = 15 * 1000L

    private val retrofitCache = ConcurrentHashMap<String, Retrofit>()

    private var serviceCache = ConcurrentHashMap<String, Any?>()

    private val okHttpClient: OkHttpClient by lazy { newOkHttp() }

    private val interceptors = mutableListOf<Interceptor>()

    /**
     * 需要在使用网络之前调用
     */
    fun addInterceptor(interceptor: Interceptor) {
        interceptors.add(interceptor)
    }

    fun <T> getService(baseUrl: String, service: Class<T>): T {
        var result: T = serviceCache[service.name] as T
        if (result == null) {
            result = getRetrofit(baseUrl).create(service)
            serviceCache[service.name] = result
        }
        return result
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        //确保 同一个url 只创建一次Retrofit对象
        synchronized(baseUrl.intern()) {
            var retrofit = retrofitCache[baseUrl]
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitCache[baseUrl] = retrofit
            }
            return retrofit!!
        }
    }

    private fun newOkHttp(): OkHttpClient {

        return OkHttpClient.Builder().apply {
            interceptors.forEach { addInterceptor(it) }
            if (LogUtil.isDebug) {
                val loginInterceptor = HttpLoggingInterceptor()
                loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(loginInterceptor)
            }
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    /**
     * 在适当的时机可以清除缓存
     */
    fun <T> removeServiceCache(service: Class<T>) {
        if (serviceCache.containsKey(service.name)) {
            serviceCache.remove(service.name)
        }
    }

    fun <T> removeRetrofitCache(baseUrl: String) {
        if (retrofitCache.containsKey(baseUrl)) {
            retrofitCache.remove(baseUrl)
        }
    }


}