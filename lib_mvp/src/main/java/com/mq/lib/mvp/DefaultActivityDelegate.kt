package com.mq.lib.mvp

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 *
 * @author Mx
 * @date 2023/02/27
 */
open class DefaultActivityDelegate : IDelegate {
    protected var activity: Activity? = null

    override fun attach(view: IBaseView) {
        if (view !is Activity) {
            throw java.lang.IllegalArgumentException("IBaseView is not an activity")
        }
        this.activity = view
    }

    override fun detach() {
        this.activity = null
    }

    protected open fun checkActivity() {
        if (activity == null) {
            throw java.lang.IllegalArgumentException("Activity must not be null")
        }
    }


    override fun getViewContext(): Context {
        checkActivity()
        return activity!!
    }

    override fun showMessage(message: String?) {
        Toast.makeText(getViewContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getResString(@StringRes res: Int): String {
        checkActivity()
        return activity!!.getString(res)
    }


}