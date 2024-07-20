package com.mq.xn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.mx.android.statelayout.BaseStateView
import com.mx.android.statelayout.State
import com.mx.android.statelayout.databinding.CommonDefStateViewBinding

/**
 *
 * @author Mx
 * @date 2023/03/10
 */
open class DefaultStateView : BaseStateView() {
    protected lateinit var binding: CommonDefStateViewBinding
        private set

    override fun createView(context: Context): View {
        binding = CommonDefStateViewBinding.inflate(LayoutInflater.from(context), null, false)
        return binding.root
    }

    override fun getState()= State.EMPTY
}