package com.mq.xn

import android.annotation.SuppressLint
import com.mq.core.BaseViewBindingActivity
import com.mq.xn.databinding.ActivityMainBinding
import com.mx.android.statelayout.BaseStateView
import com.mx.android.statelayout.State
import com.mx.android.statelayout.StateLayout

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private var stateLayout: StateLayout? = null
    override fun binding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun initView() {

        binding.stateLayout.addStateView(BaseStateView(R.layout.error_layout, State.ERROR))
        stateLayout = StateLayout.Builder(binding.target)
            .addStateView(DataEmptyStateView())
            .addStateView(NetworkErrorStateView {
                //retry
            })
            .build()
        binding.content.setOnClickListener {
            binding.stateLayout.showContent()
            stateLayout?.showContent()
        }
        binding.empty.setOnClickListener {
            binding.stateLayout.showEmpty()
            stateLayout?.showEmpty()
        }
        binding.error.setOnClickListener {
            binding.stateLayout.showError()
        }
        binding.loading.setOnClickListener {
            binding.stateLayout.showLoading()
        }
    }
}