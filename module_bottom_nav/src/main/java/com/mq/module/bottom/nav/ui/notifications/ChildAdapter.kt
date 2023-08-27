package com.mq.module.bottom.nav.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mq.module.bottom.nav.databinding.ChildItemBinding

class ChildAdapter(var items: List<String>) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {


    class ChildViewHolder(val binding: ChildItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            ChildItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.binding.tvChild.text = items[position]
    }
}