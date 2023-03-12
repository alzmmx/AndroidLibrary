package com.mq.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(@LayoutRes private val contentLayoutId: Int? = null) : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() {
            return _binding!!
        }

    private var first = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutRes = contentLayoutId ?: getLayoutId()
        return if (layoutRes != 0) {
            val view = inflater.inflate(getLayoutId(), container, false)
            initView(view)
            view
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}