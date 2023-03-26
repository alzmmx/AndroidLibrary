package com.mx.ui.statelayout.simple

import androidx.annotation.LayoutRes
import com.mx.ui.R
import com.mx.ui.statelayout.BaseStateView

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