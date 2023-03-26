package com.mx.ui.statelayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.mx.ui.databinding.CommonDefStateViewBinding
import com.mx.ui.statelayout.BaseStateView

/**
 *
 * @author Mx
 * @date 2023/03/10
 */
open class DefaultStateView : BaseStateView() {
    protected lateinit var binding: CommonDefStateViewBinding
    override fun getView(context: Context): View {
        binding = CommonDefStateViewBinding.inflate(LayoutInflater.from(context), null, false)
        return binding.root
    }
}