package com.mq.module.bottom.nav.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mq.module.bottom.nav.databinding.ItemRvBinding
import java.text.SimpleDateFormat
import java.util.*

class RvAdapter(var items: List<Long>) : RecyclerView.Adapter<RvAdapter.ViewHolder>(), ITimeIAxis {
    class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvContent.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<Long>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getText(position: Int): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(items[position])
    }

    override fun getGroupName(position: Int): String {
        return SimpleDateFormat("MM-dd", Locale.getDefault()).format(items[position])
    }

    override fun firstInGroup(position: Int): Boolean {
        if (position == 0) return true
        val pre = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(items[position - 1])
        val cur = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(items[position])
        return pre != cur
    }
}