package com.mq.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(@LayoutRes private val contentLayoutId: Int? = null) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = contentLayoutId ?: getLayoutId()
        if (layoutRes != 0) {
            setContentView(layoutRes)
        } else {
            _binding = binding()
            if (_binding == null) {
                throw NullPointerException("ViewBinding must not null,you should return the correct ViewBinding or override getLayoutId")
            }
            setContentView(binding.root)
        }

        initView()
    }

    protected open fun binding(): VB? {
        return null
    }

    protected open fun initView() {}

    @LayoutRes
    protected open fun getLayoutId() = 0
}