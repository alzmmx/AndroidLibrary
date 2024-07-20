package com.mx.tool.net

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.annotation.RequiresPermission

/**
 *
 * @author Mx
 * @date 2023/02/28
 */
object NetworkChange {
    private var isInit = false
    private lateinit var connectivityManager: ConnectivityManager

    private val listeners = mutableListOf<NetworkChangeListener>()
    private var isNetWorkConnect = false

    @MainThread
    fun init(context: Context) {
        if (!isInit) {
            isInit = true
            connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            registerNetworkChangeListener(NetworkCallback(object : NetworkChangeListener {
                override fun onChange(isConnect: Boolean) {
                    if (isNetWorkConnect != isConnect) {
                        isNetWorkConnect = isConnect
                        dispatch(isConnect)
                    }
                }
            }))
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun registerNetworkChangeListener(callback: ConnectivityManager.NetworkCallback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callback)
        } else {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), callback)
        }
    }


    fun unregisterNetworkChangeListener(callback: ConnectivityManager.NetworkCallback) {
        if (isInit) {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }


    private fun dispatch(isConnect: Boolean) {
        listeners.forEach { it.onChange(isConnect) }
    }

    fun registerNetworkChangeListener(listener: NetworkChangeListener) {
        synchronized(listeners) {
            if (!listeners.contains(listener)) {
                listeners.add(listener)
            }
        }
    }

    fun unregisterNetworkChangeListener(listener: NetworkChangeListener) {
        synchronized(listeners) {
            listeners.remove(listener)
        }
    }


    class NetworkCallback(private val callback: NetworkChangeListener) : ConnectivityManager.NetworkCallback() {
        private var isMobile = false
        private var isWiFi = false
        private val handler = Handler(Looper.getMainLooper())

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            handler.postDelayed({
                callback.onChange(true)
            }, 2000)
        }

        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                handler.removeCallbacksAndMessages(null)
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    doCallback(isMobile, true)
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    doCallback(true, isWiFi)
                }

            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            handler.postDelayed({
                doCallback(mobileConnect = false, wifiConnect = false)
            }, 2000)
        }

        private fun doCallback(mobileConnect: Boolean, wifiConnect: Boolean) {
            //避免重复回调
            if (isMobile != mobileConnect || isWiFi != wifiConnect) {
                this.isMobile = mobileConnect
                this.isWiFi = wifiConnect
                callback.onChange(isMobile || isWiFi)
            }
        }
    }

    interface NetworkChangeListener {
        fun onChange(isConnect: Boolean)
    }

    fun isConnect() = isNetWorkConnect
}