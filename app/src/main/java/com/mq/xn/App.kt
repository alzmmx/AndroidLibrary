package com.mq.xn

import android.app.Application
import com.mx.tool.database.DatabaseHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseHelper.instance.init(this)
    }
}