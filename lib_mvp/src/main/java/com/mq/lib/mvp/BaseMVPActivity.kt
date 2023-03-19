package com.mq.lib.mvp

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.mq.core.BaseActivity
import com.mq.lib.mvp.lifecycle.MVPLifecycleOwner
import com.mq.lib.mvp.lifecycle.MVPLifecycleRegistry

abstract class BaseMVPActivity(@LayoutRes contentLayoutId: Int? = null, private val delegate: IDelegate = DefaultActivityDelegate()) :
    BaseActivity(contentLayoutId), MVPLifecycleOwner, IBaseView, IDelegate by delegate {
    private var mvpViewLifecycle: MVPLifecycleRegistry? = null

    private fun initialize() {
        if (mvpViewLifecycle == null) {
            mvpViewLifecycle = MVPLifecycleRegistry(this)
        }
    }

    override fun getViewLifecycle(): MVPLifecycleRegistry {
        initialize()
        return mvpViewLifecycle!!

    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        getViewLifecycle().onCreate()
        delegate.attach(this)
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onDestroy() {
        delegate.detach()
        getViewLifecycle().onDestroy()
        super.onDestroy()
    }
}