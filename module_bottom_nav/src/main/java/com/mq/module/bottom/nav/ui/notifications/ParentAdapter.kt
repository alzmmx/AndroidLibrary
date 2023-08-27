package com.mq.module.bottom.nav.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.mq.module.bottom.nav.databinding.ParentItemBinding

class ParentAdapter(var items: List<String>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    private val caches = mutableSetOf<RecyclerView>()


    class ParentViewHolder(val binding: ParentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val adapter = ChildAdapter(mutableListOf())

        init {
            binding.rv.adapter = adapter
        }

        fun update() {
            adapter.items = mutableListOf(
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
                "I",
            )
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val holder = ParentViewHolder(
            ParentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        caches.add(holder.binding.rv)
        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.binding.title.text = items[position]
        holder.update()
        holder.binding.rv.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (RecyclerView.SCROLL_STATE_IDLE != recyclerView.scrollState) {
                    caches.filter { it != recyclerView }.forEach {
                        it.scrollBy(dx, dy)
                    }
                }

            }
        })
    }
}