package com.mq.xn.paging3;

import static androidx.room.util.CursorUtil.getColumnIndex;

import android.database.Cursor;

import kotlin.jvm.internal.Intrinsics;

public class CursorUtil {

    public static final int getColumnIndexOrThrow(Cursor c, String name) {
        String availableColumns;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(name, "name");
        int index = getColumnIndex(c, name);
        if (index >= 0) {
            return index;
        }
       /* try {
            String[] columnNames = c.getColumnNames();
            Intrinsics.checkNotNullExpressionValue(columnNames, "c.columnNames");
            availableColumns = ArraysKt.joinToString(columnNames,  null,  null,  null, 0,  null,  null, 63, null);
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            availableColumns = EnvironmentCompat.MEDIA_UNKNOWN;
        }*/
        throw new IllegalArgumentException("column '" + name + "' does not exist. Available columns: ");
    }


}
