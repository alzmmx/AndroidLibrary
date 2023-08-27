package com.mq.module.bottom.nav.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mq.lib.mvp.VBFragment
import com.mq.module.bottom.nav.databinding.FragmentNotificationsBinding

class NotificationsFragment : VBFragment<FragmentNotificationsBinding>() {

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationsBinding? {
        return FragmentNotificationsBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        val items = mutableListOf<String>()
        for (i in 0 until 30) {
            items.add("Item $i")
        }
        binding.content.adapter=ParentAdapter(items)

    }
}