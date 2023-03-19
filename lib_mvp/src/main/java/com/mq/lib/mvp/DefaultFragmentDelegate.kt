package com.mq.lib.mvp

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 *
 * @author Mx
 * @date 2023/02/27
 */
open class DefaultFragmentDelegate : IDelegate {
    protected var fragment: Fragment? = null

    override fun attach(view: IBaseView) {
        if (view !is Fragment) {
            throw java.lang.IllegalArgumentException("IBaseView is not an fragment")
        }
        this.fragment = view
    }

    override fun detach() {
        this.fragment = null
    }

    protected open fun check() {
        if (fragment == null) {
            throw java.lang.IllegalArgumentException("Fragment must not be null")
        }
    }


    override fun getViewContext(): Context {
        check()
        return fragment!!.requireContext()
    }

    override fun showMessage(message: String?) {
        Toast.makeText(getViewContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getResString(@StringRes res: Int): String {
        check()
        return fragment!!.getString(res)
    }
}