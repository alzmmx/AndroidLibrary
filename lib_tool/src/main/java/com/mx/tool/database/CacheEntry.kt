package com.mx.tool.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cache")
data class CacheEntry(
    @PrimaryKey
    val id: String,
    var data: String
)