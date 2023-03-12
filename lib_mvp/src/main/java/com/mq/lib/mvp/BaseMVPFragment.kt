package com.mq.lib.mvp

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.mq.core.BaseFragment
import com.mq.lib.mvp.lifecycle.MVPLifecycleOwner
import com.mq.lib.mvp.lifecycle.MVPLifecycleRegistry

abstract class BaseMVPFragment<VB : ViewBinding>(@LayoutRes contentLayoutId: Int? = null) : BaseFragment<VB>(contentLayoutId), MVPLifecycleOwner,
    IBaseView {

    private var mvpViewLifecycle: MVPLifecycleRegistry? = null


    private fun initialize() {
        if (mvpViewLifecycle == null) {
            mvpViewLifecycle = MVPLifecycleRegistry(this, true)
        }
    }

    final override fun getViewLifecycle(): MVPLifecycleRegistry {
        initialize()
        return mvpViewLifecycle!!

    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewLifecycle().onCreate()

    }

    @CallSuper
    override fun onDestroyView() {
        getViewLifecycle().onDestroy()
        super.onDestroyView()
    }

    override fun onDestroy() {
        getViewLifecycle().clear()
        super.onDestroy()
    }
}