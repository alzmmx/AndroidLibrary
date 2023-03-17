package com.mx.ui.statelayout.simple

import androidx.annotation.LayoutRes
import ct4.lib.ui.R
import ct4.lib.ui.widget.statelayout.State
import ct4.lib.ui.widget.statelayout.BaseStateView

/**
 *
 * @author Mx
 * @date 2023/03/08
 */
class SearchResultEmptyStateView : BaseStateView() {

    override fun getViewState(): Int {
        return StateCode.SEARCH_CODE
    }

    @LayoutRes
    override fun getLayoutId(): Int {
        return R.layout.common_search_result_empty
    }
}