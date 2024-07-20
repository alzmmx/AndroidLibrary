package com.mq.xn

import androidx.core.view.isVisible

/**
 *
 * @author Mx
 * @date 2023/03/10
 */
class DataEmptyStateView : DefaultStateView() {

    override fun onAttach() {
        binding.ivIcon.setImageResource(R.drawable.ic_empty_data)
        binding.tvTips.setText(R.string.def_empty_data)
        binding.btnAction.isVisible=false
    }
}