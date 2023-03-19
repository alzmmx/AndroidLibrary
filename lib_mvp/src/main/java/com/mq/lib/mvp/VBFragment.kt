package com.mq.lib.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.mq.core.BaseFragment

abstract class VBFragment<VB : ViewBinding>(@LayoutRes contentLayoutId: Int? = null, delegate: IDelegate = DefaultFragmentDelegate()) :
    BaseMVPFragment(contentLayoutId, delegate) {
    private var _binding: VB? = null
    protected val binding: VB
        get() {
            return _binding!!
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutRes = contentLayoutId ?: getLayoutId()
        return if (layoutRes != 0) {
            return super.onCreateView(inflater, container, savedInstanceState)
        } else {
            _binding = binding(inflater, container)
            if (_binding == null) {
                throw NullPointerException("ViewBinding must not null,you should return the correct ViewBinding or override getLayoutId")
            }
            initView(binding.root)
            binding.root
        }
    }

    protected open fun binding(inflater: LayoutInflater, container: ViewGroup?): VB? {
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}