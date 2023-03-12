package com.mq.lib.mvp

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

/**
 * 若使用ViewBinding 不可传入contentLayoutId 和重写 getLayoutId()
 * 若不使用ViewBinding，泛型VB 传ViewBinding,传入contentLayoutId 或者重写 getLayoutId()即可
 */
abstract class VBActivity<VB : ViewBinding>(@LayoutRes contentLayoutId: Int? = null) : BaseMVPActivity(contentLayoutId) {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    final override fun getContentView(): View {
        _binding = binding()
        if (_binding == null) {
            throw NullPointerException("ViewBinding must not null,you should return the correct ViewBinding or override getLayoutId")
        }
        return binding.root
    }

    protected open fun binding(): VB? {
        return null
    }


}