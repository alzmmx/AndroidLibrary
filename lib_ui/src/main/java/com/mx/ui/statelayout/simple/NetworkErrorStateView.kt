package com.mx.ui.statelayout.simple

import com.mx.ui.R


/**
 *
 * @author Mx
 * @date 2023/03/10
 */
class NetworkErrorStateView(var retry: (() -> Unit)? = null) : DefaultStateView() {

    override fun getViewState() = StateCode.NETWORK_ERROR_CODE

    override fun onAttach() {
        binding.ivIcon.setImageResource(R.drawable.common_ic_network_error)
        binding.tvTips.setText(R.string.common_def_network_error)
        binding.btnAction.run {
            setText(R.string.common_reload)
            setBackgroundResource(R.drawable.common_shape_white_r4_s1_e6ecec)
            setOnClickListener {
                retry?.invoke()
            }
        }
    }
}