package com.mq.xn.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mq.xn.databinding.ItemDataBinding
import com.mx.tool.database.CacheEntry

class DataPagingAdapter : PagingDataAdapter<CacheEntry, DataPagingAdapter.ViewHolder>(DataDiffCallback()) {
    class ViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = getItem(position)?.id
        holder.binding.tvContent.text = getItem(position)?.data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}