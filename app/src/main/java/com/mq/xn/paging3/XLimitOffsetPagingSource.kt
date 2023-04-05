package com.mq.xn.paging3

import android.database.Cursor
import android.util.Log
import androidx.room.RoomSQLiteQuery
import androidx.room.paging.LimitOffsetPagingSource
import com.google.gson.reflect.TypeToken
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper
import com.mx.tool.util.GsonUtil


class XLimitOffsetPagingSource : LimitOffsetPagingSource<UserData>(
    RoomSQLiteQuery.acquire("SELECT * FROM tb_cache GROUP BY id", 0), DatabaseHelper.instance.getAppDatabase(),
    "tb_cache"
) {
    override fun convertRows(cursor: Cursor): List<UserData> {
        var tmpId: String?
        var tmpData: String?
        val cursorIndexOfId: Int = CursorUtil.getColumnIndexOrThrow(cursor, "id")
        val cursorIndexOfData: Int = CursorUtil.getColumnIndexOrThrow(cursor, "data")
        val result: MutableList<UserData> = ArrayList(cursor.count)
        while (cursor.moveToNext()) {
            tmpId = if (cursor.isNull(cursorIndexOfId)) {
                null
            } else {
                cursor.getString(cursorIndexOfId)
            }
            tmpData = if (cursor.isNull(cursorIndexOfData)) {
                null
            } else {
                cursor.getString(cursorIndexOfData)
            }
            result.add(fromJson(tmpData!!))
        }
        return result

    }

    private fun fromJson(json: String): UserData {
        return GsonUtil.gson.fromJson(json, UserData::class.java)
    }
}