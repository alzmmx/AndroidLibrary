package com.mq.xn.paging3

import android.database.Cursor
import androidx.room.RoomSQLiteQuery
import androidx.room.paging.LimitOffsetPagingSource
import com.mx.tool.database.CacheEntry
import com.mx.tool.database.DatabaseHelper


class XLimitOffsetPagingSource : LimitOffsetPagingSource<CacheEntry>(
    RoomSQLiteQuery.acquire("SELECT * FROM tb_cache", 0), DatabaseHelper.instance.getAppDatabase(),
    "tb_cache"
) {
    override fun convertRows(cursor: Cursor): List<CacheEntry> {
        var tmpId: String?
        var tmpData: String?
        val cursorIndexOfId: Int = CursorUtil.getColumnIndexOrThrow(cursor, "id")
        val cursorIndexOfData: Int = CursorUtil.getColumnIndexOrThrow(cursor, "data")
        val result: MutableList<CacheEntry> = ArrayList(cursor.count)
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
            val item = CacheEntry(tmpId!!, tmpData!!)
            result.add(item)
        }
        return result

    }
}