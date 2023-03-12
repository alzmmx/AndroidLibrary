package com.mq.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes protected val contentLayoutId: Int? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = contentLayoutId ?: getLayoutId()
        if (layoutRes != 0) {
            setContentView(layoutRes)
        } else {
            setContentView(getContentView())
        }
        initView()
    }

    protected open fun getContentView(): View? {
        return null
    }

    protected open fun initView() {}

    @LayoutRes
    protected open fun getLayoutId() = 0
}