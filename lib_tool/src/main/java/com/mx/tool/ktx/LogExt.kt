package com.mx.tool.ktx

import android.util.Log
import com.mx.tool.BuildConfig

/**
 *
 * @author Mx
 * @date 2023/06/26
 */

fun Exception.printLog() {
    Log.e("ERROR-->", "${this.message}")
    if (BuildConfig.DEBUG) {
        this.printStackTrace()
    }
}