package com.mq.module.bottom.nav.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mq.lib.mvp.VBFragment
import com.mq.module.bottom.nav.databinding.FragmentDashboardBinding

class DashboardFragment : VBFragment<FragmentDashboardBinding>() {

    private val rvAdapter = RvAdapter(emptyList())
    private val viewModel by viewModels<DashboardViewModel>()
    override fun binding(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    override fun initView(view: View) {
        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvData.addItemDecoration(TimeAxisDecoration())
        binding.rvData.adapter = rvAdapter
        viewModel.timesLiveData.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        viewModel.createTimes()
    }
}