package com.mq.xn.paging3

import androidx.recyclerview.widget.DiffUtil
import com.mx.tool.database.CacheEntry

class DataDiffCallback : DiffUtil.ItemCallback<CacheEntry>() {
    override fun areItemsTheSame(oldItem: CacheEntry, newItem: CacheEntry): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CacheEntry, newItem: CacheEntry): Boolean {
        return oldItem.id == newItem.id
    }
}