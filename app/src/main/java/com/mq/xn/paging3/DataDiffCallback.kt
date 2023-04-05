package com.mq.xn.paging3

import androidx.recyclerview.widget.DiffUtil

class DataDiffCallback : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}