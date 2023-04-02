package com.mx.tool.database

import android.content.Context
import androidx.room.Room

class DatabaseHelper private constructor() {
    private lateinit var mAppDatabase: AppDatabase

    fun init(context: Context) {
        mAppDatabase = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "cache.db").build()
    }


    fun getAppDatabase(): AppDatabase {
        return mAppDatabase
    }

    private object SingleHelper {
        val database: DatabaseHelper = DatabaseHelper()
    }

    companion object {
        val instance = SingleHelper.database
    }

    fun cacheDao() = mAppDatabase.cacheDao()
}
