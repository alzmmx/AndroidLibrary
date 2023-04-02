package com.mx.tool.database

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cache: CacheEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(caches: List<CacheEntry>)

    @Query("SELECT * FROM tb_cache")
    fun queryAll(): MutableList<CacheEntry>

    @Query("SELECT * FROM tb_cache LIMIT :start,:count")
    fun queryAllLimit(start: Int, count: Int): PagingSource<Int, CacheEntry>

    @Query("SELECT * FROM tb_cache")
    fun queryWithPagingSource(): PagingSource<Int, CacheEntry>

    @Query("DELETE FROM tb_cache")
    fun clearAll()
}