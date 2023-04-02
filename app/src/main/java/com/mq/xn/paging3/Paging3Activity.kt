package com.mq.xn.paging3

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mq.core.BaseViewBindingActivity
import com.mq.xn.databinding.ActivityPaging3Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Paging3Activity : BaseViewBindingActivity<ActivityPaging3Binding>() {
    private val mAdapter = DataPagingAdapter()
    private val viewModel by viewModels<PagingViewModel>()
    override fun binding(): ActivityPaging3Binding {
        return ActivityPaging3Binding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.rvData.adapter = mAdapter
        lifecycleScope.launch {
            viewModel.getData().collect {
                mAdapter.submitData(it)
            }
        }
    }
}