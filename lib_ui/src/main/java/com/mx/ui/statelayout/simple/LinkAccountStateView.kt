package com.mx.ui.statelayout.simple

import ct4.lib.ui.R

/**
 *
 * @author Mx
 * @date 2023/03/08
 */
class LinkAccountStateView(var linkClick: (() -> Unit)? = null) : DefaultStateView() {

    override fun getViewState() = StateCode.LINK_CODE

    override fun onAttach() {
        binding.ivIcon.setImageResource(R.drawable.common_ic_link)
        binding.tvTips.setText(R.string.common_def_link_account)
        binding.btnAction.run {
            setText(R.string.common_link)
            setBackgroundResource(R.drawable.common_selector_bg_btn)
            setOnClickListener {
                linkClick?.invoke()
            }
        }
    }
}