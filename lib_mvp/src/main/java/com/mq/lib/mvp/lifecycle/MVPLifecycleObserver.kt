package com.mq.lib.mvp.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

open class MVPLifecycleObserver : LifecycleEventObserver {
    final override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            onCreate()
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            onDestroy()
        }
    }

    protected open fun onCreate() {

    }

    protected open fun onDestroy() {

    }
}