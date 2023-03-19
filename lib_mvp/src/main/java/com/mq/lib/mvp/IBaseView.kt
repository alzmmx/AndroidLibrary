package com.mq.lib.mvp

import android.content.Context
import androidx.annotation.StringRes

interface IBaseView {
    fun getViewContext(): Context
    fun getResString(@StringRes res: Int): String

    fun showMessage(message: String?)

    fun showLoading()
    fun hideLoading()
}