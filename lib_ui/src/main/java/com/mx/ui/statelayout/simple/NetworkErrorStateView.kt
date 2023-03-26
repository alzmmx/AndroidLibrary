package com.mx.ui.statelayout.simple

import com.mx.ui.R
import com.mx.ui.statelayout.DefaultStateView


/**
 *
 * @author Mx
 * @date 2023/03/10
 */
class NetworkErrorStateView(var retry: (() -> Unit)? = null) : DefaultStateView() {

    override fun getViewState() = StateCode.NETWORK_ERROR_CODE

    override fun onAttach() {
        binding.ivIcon.setImageResource(R.drawable.ic_network_error)
        binding.tvTips.setText(R.string.def_network_error)
        binding.btnAction.run {
            setText(R.string.reload)
            setOnClickListener {
                retry?.invoke()
            }
        }
    }
}