package com.mq.module.bottom.nav.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mq.lib.mvp.VBFragment
import com.mq.module.bottom.nav.databinding.FragmentNotificationsBinding
import com.mx.ui.statelayout.State
import com.mx.ui.statelayout.StateLayout
import com.mx.ui.statelayout.simple.StateCode

class NotificationsFragment : VBFragment<FragmentNotificationsBinding>() {

    private lateinit var stateLayout: StateLayout
    override fun binding(inflater: LayoutInflater, container: ViewGroup?): FragmentNotificationsBinding? {
        return FragmentNotificationsBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        stateLayout = StateLayout.default(binding.content)!!
        binding.btnEmpty.setOnClickListener {
            stateLayout.showEmpty()
        }
        binding.btnContent.setOnClickListener {
            stateLayout.showContent()
        }
        binding.btnNetwork.setOnClickListener {
            stateLayout.showStateView(State.create(StateCode.NETWORK_ERROR_CODE))
        }
    }
}