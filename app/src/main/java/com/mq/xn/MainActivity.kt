package com.mq.xn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.mq.core.BaseViewBindingActivity
import com.mq.xn.databinding.ActivityMainBinding
import com.mq.xn.paging3.Paging3Activity
import com.mq.xn.paging3.Repository
import com.mx.android.statelayout.BaseStateView
import com.mx.android.statelayout.State

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    override fun binding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

      val v=  LayoutInflater.from(binding.fl.context).inflate(R.layout.error_layout, null)
       // binding.fl.addView(v)

        binding.stateLayout.addStateView(BaseStateView(v, State.ERROR))
        binding.content.setOnClickListener {
            binding.stateLayout.showContent()
        }
        binding.empty.setOnClickListener {
            binding.stateLayout.showEmpty()
        }
        binding.error.setOnClickListener {
            binding.stateLayout.showError()
        }
        binding.loading.setOnClickListener {
            binding.stateLayout.showLoading()
        }
    }
}