package com.mq.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes protected val contentLayoutId: Int? = null) : Fragment() {

    private var first = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentLayoutId ?: getLayoutId(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    protected open fun initView(view: View) {}

    @LayoutRes
    protected open fun getLayoutId() = 0

    override fun onResume() {
        super.onResume()
        if (first) {
            first = false
            lazyLoad()
        }
    }


    protected open fun lazyLoad() {

    }
}